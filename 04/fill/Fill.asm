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

(BEGIN)
	// Check if a key has been pressed
	@KBD
	D=M
	@KP
	D;JNE
	@KR
	0;JMP

(KP) //Key Pushed
	@0
	D=A-1	
	@fill
	M=D	

(BSF) //Begin Screen Fill
	@8192	
	D=A
	@rBits
	M=D	
	@SCREEN
	D=A
	@position
	M=D	

(SFL) //Screen Fill Loop
	@rBits
	D=M
	@BEGIN
	D;JLE	

	@fill
	D=M
	@position
	A=M
	M=D

	@rBits
	M=M-1	
	@position
	M=M+1	

	@SFL
	0;JMP

(KR) //Key Relased
	@0
	D=A
	@fill
	M=D	
	@BSF
	0;JMP	