# Spacemacs Operations




# Spacemacs Keybinding

See also: http://www.cheatography.com/alexeberts/cheat-sheets/personal-spacemacs/

Consider going with regular vim keybindings for ; and ' because they are bound to movement commands in spacemacs. 

## Basic Operation

SPC : command prefix in Normal Mode (Orange)
ESC : Go from insert mode to Normal Mode 
C-x : Prefix for Emacs commands
C-u : Universal argument
C-c : Mode specific key-bindings

fd  : quickly pressing fd will escape from anything in spacemacs
C-g : cancel (keyboard-quit)

SPC o : reserved for the user
SPC m o : reserved for the user

## Navigation

### In Normal Mode (Orange)

#### Basic Movement

Spacemacs Default:
j : down (evil-next-line)
k : up (evil-previous-line)
l : right (evil-forward-char)
h : left (evil-backward-char)

My Modified Defaults:
k : down
l : up
j : left
; : right
h : beginning of line
' : end of line
C-d : scroll down 1/2 page (evil-scroll-down)
C-f : scroll down 1 full page 
C-u : scroll up 1/2 page (evil-scroll-up)
C-b : scroll up 1 full page
gg : move to beginning of buffer
G : move to the end of buffer

SPC j j : avy jump char (same as ace-jump)
SPC j l : avy jump line 
g ; : go to last change

#### Movement by Object

Vim Style (in Normal Mode): 
w : move forward by word (next-word)
b : move back by word (previous-word)
f<char> : move forward to next char.
% : move to matched parentheses

Emacs Style: 
C-M-; : Move forward word
C-M-j : Move backwards word

### In Insert Mode (Green)

#### Basic Movement

C-j : Left (karabiner)
C-k : Down (karabiner)
C-l : Up (karabiner)
C-; : Right (karabiner)
C-h : Beginning of line (.spacemacs)
C-' : End of line (.spacemacs) 

#### Other Movement Commands

Emacs Style: 
C-M-; : Move forward word
C-M-j : Move backwards word

Vim Style (in Normal Mode): 
w : move forward by word (next-word)
b: move back by word (previous-word)

#### Searching

/ <search text> RET : search for <search text>
n : next occurrence
N : previous occurrence
? : Search backwards

## Editing Text

### In Normal Mode 

c w : delete word from point to end of word (change word)
c i w : delete whole word point in on (change inner word)
dw : delete word
r : replace

### In Insertion Mode

dw : delete word forwards (delete after moving to word)
db : delete word backwards (delete after moving to beginning)
O : Add newline and insert before
o : Add newline and insert after

## Buffers

SPC Tab : switch last and previous buffer in current window
C-x C-s : save buffer (works in normal mode and insert)
C-x b, SPC b b : switch to buffer using helm (helm-buffers-list)
C-x C-s : save buffer
SPC b M : swap buffers

## Windows

C-x o, SPC w SPC : Jump to other window
SPC w . : initiate window micro-state
SPC w - : split window below
SPC w / : split window right
SPC w c : close a window
SPC w C : delete another window (ace-delete-window)

## Manipulating Text

SPC v : expand region 

### Cut and Paste

1) SPC v -> selects visual line mode (uppercase V selects whole line)
2) type d to cut or y to copy
3) Move to destination
4) Press P to paste before cursor for p to paste after.

## Spelling

SPC S c : helm correct spelling

## Misc 

SPC f e d : open the spacemacs dot file

## Commands

Copy whole document (Yank all the lines)

:%y+

Explanation:

% tell the next command to work on all the lines
y to yank those lines
+ to copy to the system clipboard

## Questions 

Evil quit command (like C-g)?
Copy and Paste?
Expand region? : currently set to SPC C-d 
Turn off spelling suggestions in markdown doc? 

## Ideas

### Implemented

Make the evil normal mode movement keys the same as mine e.g. :

move up evil-previous-line to : l
move down evil-next-line to : k
move left evil-backward-char : j
move right evil-forward-char : ;


