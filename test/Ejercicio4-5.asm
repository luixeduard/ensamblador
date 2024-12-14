TITLE Data Transfer Examples       (Ejercicio_4.5.asm)


INCLUDE Irvine32.inc
.data
arregloU WORD 1000b,2000h,3000h,4000h
arregloS SWORD -1,-2,-3,-4,

.code
main PROC


;  Direccionamiento Directo (word array):
	movzx eax,arregloU		; EAX = 1000h
	movzx ebx,[ab10h]	; EBX = 2000h
	movzx ecx,[arregloU+4]	; EBX = 3000h
	movzx edx,[arregloU+6]	; EBX = 4000h

	call DumpRegs

;  Direccionamiento Directo (doubleword array):
	movsx eax,arregloS				
	movsx ebx,[arregloS+2]			
	movsx ecx,[arregloS+2*2]	        
	movsx edx,[arregloS+2*3]

	call DumpRegs
	call WriteString

	exit
main ENDP
END main