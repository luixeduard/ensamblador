TITLE Data Transfer Examples       (Fibonacci.asm)

; Copia de un cadena al reves

INCLUDE Irvine32.inc
.data
	origen BYTE "Esta es la cadena de origen",0
	destino BYTE SIZEOF origen DUP('#')


.code
main PROC
	
	mov edi,0
	mov esi,SIZEOF origen -1    ; registro índice
	mov ecx,SIZEOF origen       ; contador del ciclo
	
	L1: 

		mov bl,origen[esi]    ; obtiene un carácter del origen
		mov destinosaw[edi],bl   ; lo almacena en el destino
		inc edi
		dec esi

	loop L1

	mov esi,OFFSET destino    ; desplazamiento de la variable
	mov ebx,1                 ; formato de byte
	mov ecx,SIZEOF destino    ; contador
	call DumpMem
	
	call WriteString

	exit
main ENDP
END main