# Emacs Notes

## ToDos:

Increase left margin by 3 spaces?

Install the following packages:
* markdown mode - done
* ace-jump - done
* auto-complete - done
* paredit - done
* clojure-mode - done
* clojure-test mode - done
* nrepl - done
* ac-nrepl - done
* helm - needs setup (like goto anything in sublime)
* ido mode (built in) - done
* ido mode (ubiquitious) - done
* expand-region (Highlight word under point) - done

## Consider installing
* YaSnippets http://emacsrocks.com/e06.html
* Clojure snippets are in the repo dir
* Key chord mode http://emacsrocks.com/e07.html

## Stuff to Learn
* Rectangles http://emacsrocks.com/e01.html

## Key Bindings

Emacs has a *very* rich set of modifier keys: 

Control : "C" in keybindings (e.g. (kbd "C-c") sends "Control-c" aka "^")
Meta    : "M" in keybindings (e.g. (kbd "M-c") sends "Meta-c")
Alt     : "A" in keybindings (e.g. (kbd "A-c") sends "Alt c" )
Super   : "s" in keybindings (e.g. (kbd "s-c") sends "Super-c")
Hyper   : "H" in keybindings (e.g. (kbd "H-c") sends "Hyper-c")

They don't always correspond to the physical keys, "Command", Control,
Alt / Option, Function.

In Aquamacs by default:

Apple Command key is set to "Alt"
Alt / Option key is set to "Meta"
Control key is set to "Control"
Caps Lock key is set to "Caps Lock"
Function key is not set to anything (sends fn+fn-key as normal)

Example: to bind the expand-region commands to "Command-D" do:
(global-set-key (kbd "A-d") 'er/expand-region)

### Keybindinds For Aquamacs:
I have rebound the caps-lock key to command in the system preference.

In general, key bindings are setup so that C-x is the prefix for
standard Emacs keybindings

C-c is the prefix for user defined keybindings.
Fn-f1 is the prefix for help related keybindings

The keyboard Fn key is set to "Control" (C = Control key in keybindings)
with: (setq ns-function-modifier 'control)

E.g.: (global-set-key (kbd "c-b") 'backward-word)
In this case Function-b triggers 'backward-word

### Keybindings For OSXEmacs:

I have rebound the caps-lock key to command in the system preference.

Then I have rebound the command key in emacs to ctrl with:
(setq mac-command-modifier 'ctrl)
This allows me to avoid "Emacs Pinky" by putting the control-key under
the caps-lock key (very easy to reach)

In general, key bindings are setup so that C-x is the prefix for
standard Emacs keybindings

C-c is the prefix for user defined keybindings.
Fn-f1 is the prefix for help related keybindings

The keyboard Fn key is set to "Hyper" (H = hyper key in keybindings)
with: (setq ns-function-modifier 'hyper)

E.g.: (global-set-key (kbd "H-b") 'backward-word)
In this case Function-b triggers 'backward-word

In OSXEmacs by default the keybindings are:

The Apple Command key is set to "Super" (s)
Control key is set to "Control" (C)
Alt / Option key is set to "Meta" (M)
Caps Lock key is set by default to the system binding (usually "Caps Lock")
Function key is not set to anything (sends fn+fn-key as normal)

Example: to bind the expand-region commands to "Command-D" do:
(global-set-key (kbd "s-d") 'er/expand-region)

#### OSXEmacs Keybinding Strategy

##### Assumptions:
Control is the most used key in emacs key-bindings
Meta is next most used key in key-bindings
Shift is the next most used key in key-bindings
Super is then the next most used key in key-bindings (Aquamacs, CUA and Prelude use Super)

Accessibility of keys (1=easiest, 3=hardest)
Caps-Lock : 1
Left Shift : 1
Fn : 1
Left Command : 2
Right Command : 2
Right Alt : 3
Left Alt : 3
Left Control : 3

##### Discussion:

It isn't a great idea to rebind the basic emacs movement keys because other minor modes "overload" these bindings. E.g. C-a is "beginning of line" by default in text mode but the same binding (C-a) maps to "beginning of code block" in a minor mode.

Personally, I think that the basic emacs movement keys are difficult to hit, and difficult to remember but rebinding them in config files leads to problems because these bindings are overloaded in minor modes.

The main difficulty comes when you want to have Caps Lock send Command in general Mac apps but have it send Control in Emacs only and also have the original Left-Command key send Meta or Super 

##### Goals:

Have the physical Caps-Lock key map to Command outside of Emacs and Control when in Emacs.
Make the physical Fn key send Fn when used with a function key and Alt when used with non-function keys
   Alt will send Meta in Emacs by default
Control_L sends control (default)
Option_L sends hyper (only in emacs)
Command_L sends super (default)
Command_R sends super (default)
Option_L sends meta

###### Strategy:

Map Caps Lock to No Action in system preferences (OK)

Using PCKeyboardHack assign the Caps Lock key code 110 (PC Application Key) which I think is the key with the windows symbol on standard pc keyboards. (OK)

In KeyRemap4MapBook assign PC Application Key to Command_L (OK)
(For PC Users -> Change PC Application Key -> PC Application Key to Command_L)

If you're on a MacBookPro then you'll probably want to disable Fn-j,k,l sending C-1,C-2,C-3 etc
In KeyRemap4MapBook assign Disable Numpad Hack in General (OK)
(General -> Disable Numpad Hack)

hmmm PC_APPLICATION is a key-code not a modifier so try:

All are "Enable only in Emacs"
Control + k -> Control + n
Control + l -> Control + p
Control + j -> Control + b
Control + ; -> Control + f

    <item>
        <name>Change PC Application Key to Control_L (Enable only in Emacs)</name>
        <identifier>private.app_emacs_pckey_to_control</identifier>
        <only>EMACS</only>
        <autogen>__KeyOverlaidModifier__ KeyCode::PC_APPLICATION, KeyCode::CONTROL_L</autogen>
      </item>
     <item>
        <name>Change Control + k to Control + n (Enable only in Emacs)</name>
        <identifier>private.app_emacs_control_k_to_control_n</identifier>
        <only>EMACS</only>
        <autogen>__KeyToKey__ KeyCode::K, ModifierFlag::CONTROL_L, KeyCode::N, ModifierFlag::CONTROL_L</autogen>
     </item>
    <item>
        <name>Change Control + l to Control + p (Enable only in Emacs)</name>
        <identifier>private.app_emacs_control_l_to_control_p</identifier>
        <only>EMACS</only>
        <autogen>__KeyToKey__ KeyCode::L, ModifierFlag::CONTROL_L, KeyCode::P, ModifierFlag::CONTROL_L</autogen>
     </item>
    <item>
        <name>Change Control + j to Control + b (Enable only in Emacs)</name>
        <identifier>private.app_emacs_control_j_to_control_b</identifier>
        <only>EMACS</only>
        <autogen>__KeyToKey__ KeyCode::J, ModifierFlag::CONTROL_L, KeyCode::B, ModifierFlag::CONTROL_L</autogen>
     </item>
    <item>
        <name>Change Control + ; to Control + f (Enable only in Emacs)</name>
        <identifier>private.app_emacs_control_semicolon_to_control_f</identifier>
        <only>EMACS</only>
        <autogen>__KeyToKey__ KeyCode::SEMICOLON, ModifierFlag::CONTROL_L, KeyCode::B, ModifierFlag::CONTROL_L</autogen>
     </item>
    <item>
        <name>Change Control + Quote to Control + e (Enable only in Emacs)</name>
        <identifier>private.app_emacs_control_singlequote_to_control_e</identifier>
        <only>EMACS</only>
        <autogen>__KeyToKey__ KeyCode::QUOTE, ModifierFlag::CONTROL_L, KeyCode::E, ModifierFlag::CONTROL_L</autogen>
     </item>
     <item>
        <name>Change Control + h to Control + a (Enable only in Emacs)</name>
        <identifier>private.app_emacs_control_h_to_control_a</identifier>
        <only>EMACS</only>
        <autogen>__KeyToKey__ KeyCode::H, ModifierFlag::CONTROL_L, KeyCode::A, ModifierFlag::CONTROL_L</autogen>
     </item>

 #### Other KeyRemap4MacBook configs

The following is not needed - the meta key is tacked on to the previous commands

 <item>
    <name>Change Control Option ; to Option + f (Enable only in Emacs)</name>
    <identifier>private.app_emacs_control_option_semicolon_to_option_f</identifier>
    <appendix>This sends Meta+f to Emacs (defaults to forward-word) </appendix>
    <only>EMACS</only>
    <autogen>__KeyToKey__ KeyCode::SEMICOLON, ModifierFlag::CONTROL_L | ModifierFlag::OPTION_L, KeyCode::F, ModifierFlag::OPTION_L</autogen>
 </item>


This works but isn't what I want:
In KeyRemap4MacBook assign PC Application Key to Control_L (emacs only). This doesn't exist as a default - add following code to private.xml

 <item>
    <name>Change PC Application Key to Control_L (Enable only in Emacs)</name>
    <identifier>private.app_emacs_pckey_to_control</identifier>
    <only>EMACS</only>
    <autogen>__KeyOverlaidModifier__ KeyCode::PC_APPLICATION, KeyCode::CONTROL_L</autogen>
  </item>

Example: 
 <item>
    <name>Change Command_L to Option_L (Enable at only Emacs)</name>
    <appendix>(Pass-Through Tab and Backquote)</appendix>
    <identifier>private.app_emacs_commandL2optionL_except_tab</identifier>
    <only>EMACS</only>
    <autogen>--KeyToKey-- KeyCode::TAB, ModifierFlag::OPTION_L, KeyCode::TAB, ModifierFlag::COMMAND_L</autogen>
    <autogen>--KeyToKey-- KeyCode::BACKQUOTE, ModifierFlag::OPTION_L, KeyCode::BACKQUOTE, ModifierFlag::COMMAND_L</autogen>
    <autogen>--KeyToKey-- KeyCode::COMMAND_L, KeyCode::OPTION_L</autogen>
  </item>

Alternative Strategy:

Map Caps Lock to Command in system preferences

;; Make the Command key behave like the Control key 
(setq mac-command-modifier 'control)

Capture my basic movement keys with Keyboard Maestro (Command-h, Command-; etc) and bind them to the emacs standard keys (C-a, C-e). This will keep the minor mode bindings intact.

Set Caps Lock to Command in System Prefs. This enables movement keys and command keybindings in other Mac apps.

Make L-Command send Control only in Emacs with KeyRemap4MacBook
Make Function key send Alt -> which sends Meta to Emacs (in emacs.d?)
Make Left Command send Command (do nothing) -> Super (s)
Make Right Option key send Control only in Emacs with KR4MB (?)

Setting Fn to Meta would allow easy access to Shift Meta and Control Meta combinations.

##### Future Keymapping Ideas

Map Shift + Char to Uppercase Char but Shift becomes Meta if Control key is held down.

Map control key to 

### Getting Help

describe-key              :  <f1> k, <help> k
describe-function         :  <f1> f, <help> f
describe-variable         :  <f1> v, <help> v
apropos                   :  <f1> a  <help> a
info-reader               :  <f1> i  <help> i
man-pages                 :  M-x man         
describe-mode             :  C-h m           
describe-key-briefly      :  C-h c           

Go back to the previous topic in the help C-c C-b

### Executing Commands

execute-extended-command : C-x C-m, C-c RET
quit                     : C-x C-c
repeat last command      : C-x z 

### Basic Movement

These are bound in Keyboard Maestro to avoid clobbering kill-line etc.

next-line            : Command-k (Keyboard Maestro sends C-n)
previous-line        : Command-l (Keyboard Maestro sends C-p)
backward-char        : Command-j (Keyboard Maestro sends C-f)
forward-char         : Command-; (Keyboard Maestro sends C-b)
beginning of line    : Command-h (Keyboard Maestro sends C-a)
end of line          : Command-' (Keyboard Maestro sends C-e)
backward-sentence    : M-a  (consider rebind to M-h)
forward-sentence     : M-e  (consider rebind to M-')
backwards-para       : C-<up arrow>
forwards-para        : C-<down arrow>
<pgdn> (prior)       : fn-<up arrow>
<next>  (pgup)       : fn-<down arrow>
beginning-of-buffer  : fn-<left arrow>
end-of-buffer        : fn-<right arrow>
recenter-top-bottom  : C-l, H-l

forward-by-sexp       : C-M-f, C-M-<right arrow> (useful!)
backward-by-sexp      : C-M-b, C-M-<left arrow> (useful!)
back-to-indentation   : M-m (consider rebind)

beginning of defun    : C-M-a (consider rebind - C-M-< ?)
end of defun          : C-M-e (consider rebind - C-M-> ?)

repeat command        : C-u <number> <command> C-u 8 C-f = move 8 chars 

ace-jump-word-mode    : C-c SPC
ace-jump-char-mode    : C-c C-u SPC
ace-jump-line-mode    : C-c C-u C-u SPC

transpose-char        : C-t (drag char behind point fwd)
transpose-line        : M-<up arrow>,<down arrow>

### Smex (Meta X replacement)

Next match            : C-s
Prev match			  : C-r

### iSearch

Can also be considered a movement command
begin-isearch         : A-f

### Region

set-mark-command     : C-space
cancel mark           : C-g

### Killing Cutting and Pasting

kill-region           : H-k, C-w
set-mark-command      : C-space
exchng point & mark   : C-x C-x
jump prev mark        : C-u C-space (set mark, cancel, c-u c-spc to return)
kill-ring-save        : M-w      (save the region as if killed aka copy)
cut                   : A-x      (with region selected)
copy                  : A-c      (with region selected)
paste                 : A-v      (with region selected)
cua-paste-pop         : M-y      (same as 'yank-pop)
delete-char-fwd       : C-d
delete-word-backwards : M-DEL
delete-word-forward   : M-d
kill to end of sent   : M-k
backwards kill line   : C-u 0 C-k

### Selection

Selection is more than an little funky in Emacs
expand region           : A-d (repeat to expand to semantic units)

### Buffers and Files

Switch-buffer              : C-x b                                        
list-buffers               : C-x C-b (d to delete buffer and x to execute)
kill-selected-buffer       : C-k (from within the mini-buffer)            
find-file                  : C-x c-f                                      
visit-recent-files         : C-x f, C-x, C-r                              
find file under point      : C-x p                                        
show-file-name             : C-c n                                        
mac-key-save-file          : A-s                                          
save-some-buffers          : C-x s (save all files)                       

### Dired

Dired is a very powerful way of navigating to files without leaving Emacs

go up a directory          : ^
Next subdir                : >
Prev subdir                : <

### Window Control

Split Window Vertically    : C-x 3
Split Window Horizotally   : C-x 2
Close Window (Unsplit)     : C-x 0
Current Window Only        : C-x 1
Focus in Direction         : Shift-arrow keys
'other-window              : C-x o
Open file in other window  : C-x M-f
Save Window Config         : C-x r w a
Restory Window Config      : C-x r j a
Window Down                : Shift-down
Window Up                  : Shift-up
Window Right               : Shift-right
Window Left                : Shift-left

### Editing

Transpose words			   : M-t                                          
Comment Region             : M-; (comments if region, else column comment)
Hippy Expand               : M-/                                          
Indent Region              : C-M-\                                        

### Clojure Commands

Eval last Exp in Repl      : 
Reindent region            : C-M-\ 
Fold functions             : TODO
Mark defun                 : C-M-h 

## Customizations

### Fonts

For a specific font: M-x customize-face RET

## Programming

### Paredit

Force delete backwards  : C-u DEL
Raise over parent       : M-r
splice                  : M-s
slurp-forward           : C-<left arrow>
slurp-backward          : C-<right arrow>

### Clojure Buffer ==> nREPL Interaction

Eval top evel form       : C-c C-c
Eval the ns form         : C-c C-n
Eval form preceding pont : C-c C-p
Clear REPL buffer        : C-c M-o
jump to symbol def       : M-.
Return to pre-jump point : M-,
Backtraces on errors     : (auto)
Load current buffer      : C-c C-k 
Describe current symbol  : C-c C-d 
auto-completion          : (auto)
Auto-doc in mini-buffer  : (auto)
Visit the current nREPL  : C-c C-z

### nREPL buffer

Jack in to specific file : C-u M-x
Close paren and eval     : C-RET
Interupt pending evals   : C-c C-b
Prev / Next in history   : C-up / C-down
Search-fwd in history    : M-s
Describe current symbol  : C-c C-d
Clear nREPL buffer       : C-c M-o
Complete symbol          : TAB
Close nREPL buffer       : M-x RET nrepl-close

### nREPL Introspection in Clojure Buffer

Inspect symbol           : C-c C-i (on any expression, will prompt to accept)
Next object              : TAB (Shift / TAB)
Inspect subobject        : RET
Pop to Parent obj        : l (lower-case "L")
Refresh inspector        : g

