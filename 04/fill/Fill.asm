// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.


(BEGIN)// Check if a key has been pressed
	@KBD
	D=M
	@7
	D;JNE
	@37
	0;JMP
(KP) //Key Pushed
	@0
	D = A - 1	
	@10
	M = D	
(BSFL) //Begin Screen Fill Loop
	@8192	
	D = A
	@15
	M = D	
	@17
	D = A
	@19
	M = D	
(SFL) //Screen Fill Loop
	@15
	D = M
	@0
	D;JLE	
	@10
	D = M
	@19
	A = M
	M = D
	@15
	M = M - 1	
	@19
	M = M + 1	
	@21
	0;JMP
(KR) //Key Relased
	@0
	D = A
	@10
	M = D
	@12
	0;JMP