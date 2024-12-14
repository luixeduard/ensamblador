include Irvine32.inc
INCLUDE macros.inc
MAXBUF = 128
CLAVE = 239

.data
msg1 BYTE "Ingrese el texto a cifrar:  ",0
msg2 BYTE "Ingrese la clave de cifrado ",0
cifrado BYTE "Texto cifrado:     ",0
descifrado BYTE "Texto Normal:     ",0
bufer BYTE MAXBUF+1 DUP(0)
msgC BYTE "Ingrese la clave:  ",0
tamBufer DWORD ?
tamClv DWORD ?
nombreE BYTE 80 DUP(0)
manejadorEntrada HANDLE ?
clv BYTE 21 DUP(0)
.code
main PROC
	call IntroducirCadena
	call TraducirBufer
	mov edx,OFFSET cifrado
	call MostrarMensaje
	call TraducirBufer
	mov edx,OFFSET descifrado
	call MostrarMensaje
	CALL WaitMsg
	exit
main ENDP

;Pide una cadena de texto al usuario
IntroducirCadena PROC
	pushad
	mov edx,OFFSET msgC
	call WriteString
	mov   edx, OFFSET clv           ; apunta a clv
	mov   ecx, SIZEOF clv           ;
	call  ReadString                ; recibe la cadena de entrada
	mov   tamClv, eax       


	mWrite "Escriba el nombre del archivo (no olvide el tipo):   " ;Pide al usuario el nombre del archivo 
	mov edx, OFFSET nombreE									;mueve a edx el apuntador de donde se guarda el nombre
	mov ecx, SIZEOF nombreE									;mueve a ecz el tamonio de donde se guardara el nombre
	call ReadString													;lee la cadena introducida por el usuario
	
	mov edx,OFFSET nombreE
	call OpenInputFile
	mov manejadorEntrada,eax
	mov edx,OFFSET bufer
	mov ecx, MAXBUF
	call ReadFromFile
	mov bufer[eax],0 

	call Crlf
	popad
	ret
IntroducirCadena ENDP

;Muestra el mensaje ya sea cifrado o Descifrado
MostrarMensaje PROC
	pushad
	call WriteString
	mov edx,OFFSET bufer
	call WriteString
	call Crlf
	call Crlf
	popad
	ret
MostrarMensaje ENDP

;Traduce el contenido del bufer ya sea cifrar o descifrar
TraducirBufer PROC
	pushad
	mov ecx,tamBufer
	mov esi,0
	mov edi, 0	
L1:
	.IF  edi <= tamClv
	mov  al, clv[edi]
	.ELSE
	mov  edi, 0
	mov  al, clv[edi]
	.ENDIF
	xor bufer[esi],al	
	inc esi
	inc edi
	loop L1
	popad
	ret
TraducirBufer ENDP
END main
