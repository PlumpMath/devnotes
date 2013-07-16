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

Emacs has a *very* rich set
of modifier keys: 

Alt     : "A" in keybindings (e.g. (kbd "A-c") sends "Alt c" )
Control : "C" in keybindings (e.g. (kbd "C-c") sends "Control-c" aka "^")
Meta    : "M" in keybindings (e.g. (kbd "M-c") sends "Meta-c")
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

### Keybindins For OSXEmacs:

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

### Getting Help

'describe-key         :  <f1> k, <help> k
'describe-function    :  <f1> f, <help> f
'describe-variable    :  <f1> v, <help> v
'apropos              :  <f1> a  <help> a
'info-reader          :  <f1> i  <help> i
'man-pages            :  M-x man
describe-mode         : C-h m


### Executing Commands

'execute-extended-command : C-x C-m, C-c RET
'quit                     : C-g

### Basic Movement

These are bound in Keyboard Maestro to avoid clobbering kill-line etc.

'next-line            : Command-k (Keyboard Maestro sends C-n)
'previous-line        : Command-l (Keyboard Maestro sends C-p)
'backward-char        : Command-j (Keyboard Maestro sends C-f)
'forward-char         : Command-; (Keyboard Maestro sends C-b)
'beginning of line    : Command-h (Keyboard Maestro sends C-a)
'end of line          : Command-' (Keyboard Maestro sends C-e)
'backward-sentence    : M-a  (consider rebind to M-h)
'forward-sentence     : M-e  (consider rebind to M-')
'backwards-para       : C-<up arrow>
'forwards-para        : C-<down arrow>
'<pgdn> (prior)       : fn-<up arrow>
'<next>  (pgup)       : fn-<down arrow>
'beginning-of-buffer  : fn-<left arrow>
'end-of-buffer        : fn-<right arrow>
'recenter-top-bottom  : C-l, H-l

forward-by-sexp       : C-M-f, C-M-<right arrow> (**useful!)
backward-by-sexp      : C-M-b, C-M-<left arrow> (**useful!)
back-to-indentation   : M-m (consider rebind to someting else)

repeat command        : C-u <number> <command> C-u 8 C-f = move 8 chars 

'ace-jump-word-mode   : C-c SPC
'ace-jump-char-mode   : C-c C-u SPC
'ace-jump-line-mode   : C-c C-u C-u SPC

transpose-char    : C-t (drag char behind point fwd)
transpose-line    : M-<up arrow>,<down arrow>

### iSearch

Can also be considered a movement command

begin-isearch         : A-f


### Region

'set-mark-command     : C-space
cancel mark           : C-g

### Killing Cutting and Pasting

'kill-region           : H-k, C-w
'set-mark-command      : C-space
'exchng point & mark   : C-x C-x
'jump prev mark        : C-u C-space (set mark, cancel, c-u c-spc to return)
'kill-ring-save        : M-w      (save the region as if killed aka copy)
'cut                   : A-x      (with region selected)
'copy                  : A-c      (with region selected)
'paste                 : A-v      (with region selected)
'cua-paste-pop         : M-y      (same as 'yank-pop)
'delete-char-fwd       : C-d
'delete-word-backwards : M-DEL
'delete-word-forward   : M-d
'kill to end of sent   : M-k
'backwards kill line   : C-u 0 C-k

### Selection

Selection is more than an little funky in Emacs
expand region           : A-d (repeat to expand to semantic units)


### Buffers and Files

Switch-buffer          : C-x b
list-buffers           : C-x C-b (d to delete buffer and x to execute)
kill-selected-buffer   : C-k (from within the mini-buffer)
find-file              : C-x c-f
visit-recent-files     : C-x f, C-x, C-r
find file under point  : C-x p
show-file-name         : C-c n
mac-key-save-file      : A-s
save-some-buffers      : C-x s (save all files)

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

Transpose words			: M-t
Comment Region          : M-; (comments if region, else column comment)
Hippy Expand            : M-/ 


### Clojure Commands

Eval Exp in Repl      :
Reindent region       : 
Fold functions        : TODO

## Customizations

### Fonts

For a specific font: M-x customize-face RET

## Programming

### Paredit

Force delete backwards  : C-u DEL
Raise over parent       : M-r

### Clojure Buffer nREPL 

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

