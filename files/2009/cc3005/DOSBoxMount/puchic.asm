	;; ****************************************
	;; Author: Carlos Eduardo Lopez Camey
	;; Date: Oct 6, 2009
	;; UVG-CC3005 - Programacion en Assembler
	;; PreLab7.ASM Pre Laboratorio 7
	;;
	;; Descripcion:
	;; 
	;; ****************************************

	;;
	;; Macros
	;;
	CopiarCadena_De_A	MACRO	DesdeCual, EnCual, Tamano
		MOV CL, Tamano
		CLD		; de izquierda a derecha
		LEA SI, DesdeCual	; copiar de
		LEA DI, EnCual	; pegar en
		REP MOVSB	; copiar bytes Tamano veces
	ENDM

	EscribirMensaje	MACRO	Cadena
		PUSH AX
		LEA DX, Cadena
		CALL Desplegar
		POP AX
		CALL ImprimirEnter
	ENDM

	LeerCadenaYGuardarEn MACRO Lista
		PUSH AX
		PUSH DX
							
		LEA DX, Lista
		CALL LeerCadena
		
		POP DX
		POP AX
	ENDM
	
	getPosicionDeMemoria	MACRO	Fila,Columna	
		PUSH AX
		PUSH DX	
		PUSH BX
		
		MOV AX, WORD PTR Columna	
		MOV DX, 0	
		MOV BX, 12		
		MUL BX				;AX:=AX*Op
		MOV PosicionDeMemoria, AX			; Posicion en memoria = Columna en memoria
		
		MOV AX, WORD PTR Fila
		MOV DX, 0
		MOV BX, 120
		MUL BX
		ADD PosicionDeMemoria, AX			; Posicion en memoria = PosicionEn Memoria + Fila en memoria
		
		POP BX
		POP DX
		POP AX
	ENDM
	
	ConvertirNumeroACadena			MACRO	Numero	, 	Cadena
		LOCAL Calcular,MultiplicarOtravez,Proceder,TerminarDeMultiplicar
		PUSH AX				; para multiplicar y dividir
		PUSH CX				; contador
		PUSH DX				; usado para multiplicar con contador
		PUSH BX				; Indice
		MOV CX, 4			;maximo numero a escribir es 2^16-1, es decir 65535 (5 digitos)
		MOV BX, 0
Calcular:							;a hacer 10 ^ CX
		PUSH CX
		PUSH DX
		MOV AX, 1				; AX = 1
MultiplicarOtraVez:							
		MOV DX, 10				
		MUL DX					; AX = DX * AX, i.e. AX = 10*AX, CX veces
		DEC CX
		CMP CX, 0
		JE TerminarDeMultiplicar
		JMP MultiplicarOtraVez
TerminarDeMultiplicar:		
		POP DX
		POP CX
Proceder:
		MOV TemporalWord, AX
		MOV AX, PosicionDeMemoria			;Op=word: AX:=DX:AX / Op, DX = resto
		MOV DX, 0
		DIV	TemporalWord		; Es decir, AX = Numero / (10 ^ CX), DX = resto
		MOV PosicionDeMemoria, DX			

		ADD AX, 30H				;AX = Num / (10 ^ CX) + 30H
		MOV WORD PTR DiezCaracteresTempArreglo[BX],  AX
		EscribirMensaje DiezCaracteresTempArreglo
		INC BX
		DEC CX
		CMP CX, 0
		JNZ Calcular
		; Las unidades quedaron en PosicionDeMemoria
		ADD PosicionDeMemoria, 30H
		MOV AX, PosicionDeMemoria
		MOV WORD PTR DiezCaracteresTempArreglo[BX],  AX

		POP BX
		POP DX
		POP CX
		POP AX
	ENDM
	
	kmels_PedirFilaYColumna	MACRO
		PUSH DX
		EscribirMensaje	kmels_Msg_PedirFila
		LEA DX, ListaDe1CaracterTemp
		CALL	LeerCadena			
		CALL ImprimirEnter				
		; Pasarlo a FilaActual
		MOV DL, CaracterTempArreglo
		MOV	FilaActual, DL
		SUB	FilaActual, 30H
			
		EscribirMensaje kmels_Msg_PedirColumna
		LEA DX, ListaDe1CaracterTemp
		CALL	LeerCadena	
		CALL ImprimirEnter
								
		; Pasarlo a FilaActual
		
		MOV DL, CaracterTempArreglo
		MOV	ColumnaActual, DL
		SUB	ColumnaActual, 30H
		POP DX
	ENDM
	
	;; Fin de Macros
	.model small
	.stack 64
	.data

	;; ----------------------------------------
	;; Definicion de algunos datos
	;; ----------------------------------------

	InstruccionesLabel	DB	'Ingresar combinacion de teclas','$'  ;Instrucciones para el usuario
	TeclaEnter 		DB	0DH,0AH,'$'
	
	ListaDe1CaracterTemp 			LABEL	BYTE		      	; Inicio de la lista de parametros
	CaraacterTempLongitudMaxima		DB	2		     						; Longitud maxima de la cadena
	CaracterTempLongitudDeLaCadena	DB	?		 				     		; Longitud real de la cadena
	CaracterTempArreglo				DB	2 DUP (' '),'$'	      				; Arreglo de caracteres
	
	
	ListaDe10CaracteresTemp 		LABEL	BYTE		      	; Inicio de la lista de parametros
	DiezCaracteresTempMaxLen		DB	11		     						; Longitud maxima de la cadena
	DiezCaracteresTempLenReal		DB	?		 				     		; Longitud real de la cadena
	DiezCaracteresTempArreglo		DB	11 DUP (' '),'$'	      				; Arreglo de caracteres
	
	
	PosicionDeMemoria	DW	?
	FilaActual			DB	?
	ColumnaActual		DB	?
	
	ColumnaEnMemoria	DW	?			;para el calculo de getposicionenmemoria
	FilaEnMemoria		DW 	?			;para el calculo de getposicionenmemoria
	LaMeraMatriz		DB 		720 DUP  (' ')
	TemporalWord			DW	?
	
	; kmels!

	Msg_IrAManejoDeCelda			DB 	'Manejo de celda','$'				; Manejo de celda
	Msg_IrAFunciones		DB	'Funciones $'
	Msg_IrAArchivo		DB	'Archivo $'
	Msg_IrAAyuda		DB 	'Ayuda $'
	Msg_IrAFindYReplace	DB	'Find y replace $'
	Msg_IrASalir		DB	'Salir $'
	
	Msg_ManejoDeCelda1	DB 	'Ingresar/Modificar Celda (String)$'
	Msg_ManejoDeCelda1_1	DB	'Ingresar el string de 10 letras pues$'
	Msg_ManejoDeCelda2	DB	'Ingresar/Modificar Celda (Numero)$'
	Msg_ManejoDeCelda3	DB	'Cambio de atributo$'
	Msg_ManejoDeCelda4	DB	'Escribir/Mostrar Celda$'
	
		
	PresionoFuncion		DB	'Presiono Funcion','$'			
	NOPresionoFuncion	DB	'NO Presiono Funcion','$'			
	
	kmels_Msg_PedirFila		DB	'Fila: $'
	kmels_Msg_PedirColumna	DB	'Columna: $'
	
	
	PresionoCombinacionIncorrecta	DB	'Presiono Combinacion Incorrecta','$'
	
	
	
	
	;; ----------------------------------------
	;; Inicio del codigo
	;; ----------------------------------------

	.CODE

	;; ----------------------------------------
	;; Procedimiento para leer la cadena
	;; ----------------------------------------
	LeerCadena PROC NEAR
		MOV AH,0AH
		INT 21H
		RET
	LeerCadena ENDP

	;; ----------------------------------------
	;; Procedimiento para desplegar
	;; ----------------------------------------
	Desplegar PROC NEAR
		MOV AH, 09H	; Peticion para desplegar
		INT 21H		; llama al DOS
		RET
	Desplegar ENDP

	;; ----------------------------------------
	;; Procedimiento para escribir un enter (caracter 13)
	;; ----------------------------------------

	ImprimirEnter	PROC NEAR
		LEA DX, TeclaEnter
		CALL Desplegar
		RET
	ImprimirEnter	ENDP

	;; ----------------------------------------
	;; Inicio
	;; ----------------------------------------

	Principal	PROC FAR
		MOV AX, @data	; Inicializar el segmento de datos
		MOV DS, AX
		MOV ES, AX
		
		;inicio calcular posmemoria
		kmels_PedirFilaYColumna
		
		;getPosicionDeMemoria	FilaActual,ColumnaActual
		
		MOV AX, WORD PTR ColumnaActual	
		MOV DX, 0	
		MOV BX, 12		
		MUL BX				;AX:=AX*Op
		MOV PosicionDeMemoria, AX			; Posicion en memoria = Columna en memoria
		
		MOV AX, WORD PTR FilaActual
		MOV DX, 0
		MOV BX, 120
		MUL BX
		ADD PosicionDeMemoria, AX			; Posicion en memoria = PosicionEn Memoria + Fila en memoria
		
		
		ConvertirNumeroACadena			PosicionDeMemoria, DiezCaracteresTempArreglo
		EscribirMensaje	DiezCaracteresTempArreglo
		
		
		;end calcular
		
;PeticiÃ³n de entrada
PedirTecla:		
		EscribirMensaje InstruccionesLabel
		MOV AH,00H	; PeticiÃ³n entrada del teclado
		INT 16H		; Llama a DOS
		CMP	AL, 0	; Â¿Se presionÃ³ tecla de funciÃ³n?
		JNZ	IrASalir	; no, es un caracter ASCII
		
		CMP AH, 3BH 		  	; presiono f1 ?
		JE IrAManejoDeCelda     ; si
		CMP AH, 2EH				; Presiono Alt+C?
		JE IrAManejoDecelda		;si
		
		CMP	AH, 3CH				;Presiono F2?
		JE	IrAFunciones		; si		
		CMP	AH, 21H				;Presiono Alt+F
		JE	IrAFunciones		; si
		
		CMP	AH, 3DH				;Presiono F3?
		JE	IrAArchivo			; si		
		CMP	AH, 1EH				;Presiono Alt+A
		JE	IrAArchivo			; si
		
		CMP	AH, 3EH				;Presiono F4?
		JE	IrAAyuda			; si		
		CMP	AH, 15H				;Presiono Alt+F
		JE	IrAAyuda			; si
		
		CMP AH, 3FH				;Presiono F5?
		JE IrAFindYReplace		; si
		CMP	AH, 13H				; Presiono Alt+R?
		JE	IrAFindYReplace		; si
		
		CMP	AH, 40H				; Presiono F6 ?
		JE	IrASalir			; si		
		CMP AH, 1FH				; Presiono Alt+S?
		JE IrASalir				; si
		
		
		EscribirMensaje	PresionoCombinacionIncorrecta
		JMP PedirTecla
IrASalir:
		EscribirMensaje	Msg_IrASalir
		JMP Salir
		
IrAManejoDeCelda: 	
		EscribirMensaje	Msg_IrAManejoDeCelda
		JMP ManejoDeCelda

IrAFunciones:
		EscribirMensaje	Msg_IrAFunciones
		JMP PedirTecla
		
IrAArchivo:
		EscribirMensaje	Msg_IrAArchivo
		JMP PedirTecla
		
IrAAyuda:
		EscribirMensaje	Msg_IrAAyuda
		JMP PedirTecla

IrAFindYReplace:
		EscribirMensaje	Msg_IrAFindYReplace
		JMP PedirTecla

ManejoDeCelda:
		EscribirMensaje Msg_ManejoDeCelda1
		EscribirMensaje Msg_ManejoDeCelda2
		EscribirMensaje Msg_ManejoDeCelda3
		EscribirMensaje Msg_ManejoDeCelda4
		
		MOV AH, 00H			;Pedir Algo
		INT	16H
		CMP AL, 31H			;es uno
		JE	IrAModificarCeldaString
		
		
		
IrAModificarCeldaString:
		kmels_PedirFilaYColumna
		
		;getPosicionDeMemoria	FilaActual,ColumnaActual
		
		;EscribirMensaje	DiezCaracteresTempArreglo
		
		;LeerCadenayGuardarEn	ListaDe10CaracteresTemp
		
		
Salir:
		MOV AH, 4CH		; Salida al DOS
		INT 21H

Principal	ENDP
	END Principal