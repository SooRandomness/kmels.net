;Universidad del Valle de Guatemala
;Departamento de Computaci�n

;Programa de Ejemplo No. 1
;Hello world

.MODEL SMALL
.STACK 64
.DATA                    

; Definicion de datos

CADENA  DB      'Hello world !!','$'
;-------------------------------------------------

; Inicio del codigo
.CODE
;-----------------------------------------------------------
principal	PROC
       MOV AX,@data            ;inicializa segmento de datos
       MOV DS,AX
       MOV AH, 09h     	;muestra mensaje en pantalla
       LEA DX, CADENA
       INT 21H
	      
       MOV AH, 4CH   		;salida al DOS
	INT 21H
principal	ENDP
;-----------------------------------------------------------
END principal