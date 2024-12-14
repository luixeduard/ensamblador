TITLE Data Transfer Examples       (Ejercicio_4.7.asm)
; Escriba un programa que implemente la siguiente expresión aritmética:  EAX = -val2 + 7 – val3 + val1


INCLUDE Irvine32.inc
.data
	val1 SDWORD 8
	val2 SDWORD -15515
	val3 SDWORD 20

.code
main PROC
	
	mov eax,0       ;0000h
	sub eax,val2    ;000Fh
	add eax,7       ;0016h
	sub eax,val3    ;0002h
	add eax,val1    ;000Ah


	call DumpRegs
	call WriteString

	exit
main ENDP
END main