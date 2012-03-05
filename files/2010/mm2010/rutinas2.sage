
#devuelve el polinomio interpolante cubico entre dos puntos dados y los valores de sus derivadas en ese punto
def hermiteInterpolanteCubico(x0,y0,yprima0,x1,y1,yprima1):
    #a = var('a')
    #b = var('b')
    c = var('c')
    d = var('d')
    a = y0
    b = yprima0
    p(x) = a + b*(x-x0)+c*(x-x0)**2+d*(x-x0)**2*(x-x1)

    #calcular valor de c
    ecuacion1 = p(x1) == y1
    valor_c = solve([ecuacion1],c,solution_dict=True)[0][c]

    print a
    print b
    print valor_c
    #calcular valor de d    
    p_(x) = b + 2*valor_c*(x-x0)+2*d(x-x0)*(x-x1)+d*(x-x0)**2
    ecuacion2 = p_(x1) == yprima1
    valor_d = solve([ecuacion2],d,solution_dict=True)[0][d]
    polinomioCubicoInterpolante(x) = a + b*(x-x0)+valor_c*(x-x0)**2+valor_d*(x-x0)**2*(x-x1)
    print valor_d
    return polinomioCubicoInterpolante

# Imprime (i.e. no devuelve nada) en cada linea el polinomio cubico del trazador y dice para que valores de x es "valido" interpolar. los puntos son mandados de la misma manera siempre [(x_0,y_0),(x_1,y_1)...(x_n,y_n)], pueden estar desordenados
def trazadoresCubicosImpreso(puntos):
    puntos.sort()
    Xs = []
    As = []
    Bs = []
    Cs = []
    Ds = []
    Hs = []
    Ls = [1]
    Us = [0]
    Zs = [0]
    Alpha = []
    n=len(puntos)-1
    for x,y in puntos:
        As.append(y)
        Xs.append(x)
    i=0
    while i<n:
        Hs.append(Xs[i+1]-Xs[i])
        i=i+1
    i=0
    while i<n-1:
        primerTermino = ((3/Hs[i+1])*(As[i+2]-As[i+1]))
        segundoTermino = ((3/Hs[i])*(As[i+1]-As[i]))
        Alpha.append(primerTermino-segundoTermino)
        i=i+1
    i=1
    while i<n:
       Ls.append(2*(Xs[i+1]-Xs[i-1])-Hs[i-1]*Us[i-1])
       Us.append(Hs[i]/Ls[i])
       Zs.append((Alpha[i-1]-Hs[i-1]*Zs[i-1])/Ls[i])
       i=i+1
    Ls.append(1)
    Zs.append(0)
    Cs.append(0)
    j=n-1
    i=0
    while j>=0:
        Cs.append(Zs[j]-(Us[j]*Cs[i]))
        Bs.append(((As[j+1]-As[j])/Hs[j])-((Hs[j]*(Cs[i]+2*Cs[i+1])/3)))
        Ds.append((Cs[i]-Cs[i+1])/(3*Hs[j]))
        j = j-1
        i = i+1
    listaTemp = []
    retorno2 = []
    i=0
    Cs.reverse()
    Bs.reverse()
    Ds.reverse()
    while i<n:
        p=[As[i],Bs[i],Cs[i],Ds[i],Xs[i]]
        listaTemp.append(p)
        i=i+1
    contador=0
    linea=80
    for k in listaTemp:
        linea="%s+%s*(x-%s)+%s*(x-%s)^2+%s*(x-%s)^3  /\ %s<=x<=%s" %(k[0],k[1],k[4],k[2],k[4],k[3],k[4],Xs[contador], Xs[contador+1])
        contador=contador+1
        retorno2.append(linea)
        print linea
 


# Devuelve el conjunto de los trazadores de la forma [p1(x),p2(x),p3(x)...pn(x)] en donde cada pi(x) representa una funcion polinomial, los puntos son mandados de la misma manera siempre [(x_0,y_0),(x_1,y_1)...(x_n,y_n)], pueden estar desordenados
def trazadoresCubicosConjunto(puntos):
    puntos.sort()
    Xs = []
    As = []
    Bs = []
    Cs = []
    Ds = []
    Hs = []
    Ls = [1]
    Us = [0]
    Zs = [0]
    Alpha = []
    n=len(puntos)-1
    for x,y in puntos:
        As.append(y)
        Xs.append(x)
    i=0
    while i<n:
        Hs.append(Xs[i+1]-Xs[i])
        i=i+1
    i=0
    while i<n-1:
        primerTermino = ((3/Hs[i+1])*(As[i+2]-As[i+1]))
        segundoTermino = ((3/Hs[i])*(As[i+1]-As[i]))
        Alpha.append(primerTermino-segundoTermino)
        i=i+1
    i=1
    while i<n:
       Ls.append(2*(Xs[i+1]-Xs[i-1])-Hs[i-1]*Us[i-1])
       Us.append(Hs[i]/Ls[i])
       Zs.append((Alpha[i-1]-Hs[i-1]*Zs[i-1])/Ls[i])
       i=i+1
    Ls.append(1)
    Zs.append(0)
    Cs.append(0)
    j=n-1
    i=0
    while j>=0:
        Cs.append(Zs[j]-(Us[j]*Cs[i]))
        Bs.append(((As[j+1]-As[j])/Hs[j])-((Hs[j]*(Cs[i]+2*Cs[i+1])/3)))
        Ds.append((Cs[i]-Cs[i+1])/(3*Hs[j]))
        j = j-1
        i = i+1
    listaTemp = []
    retorno2 = []
    i=0
    Cs.reverse()
    Bs.reverse()
    Ds.reverse()
    while i<n:
        p=[As[i],Bs[i],Cs[i],Ds[i],Xs[i]]
        listaTemp.append(p)
        i=i+1
    contador=0
    linea=80
    conjuntoDeTrazadores = []
    for i in listaTemp:
        trazador(x) = k[0]+k[1]*(x-k[4])+k[2]*(x-k[4])**2+k[3]*(x-k[4])**3
        conjuntoDetrazadores.append(trazador)
    return conjuntoDeTrazadores


#neville.sage
#Calcula los valores de lagrange mediante el algoritmo de neville

# -*- coding: utf-8 -*-
import numpy

def neville( X, tuplas ):
    Q=numpy.matrix([[0]*len(tuplas)]*len(tuplas),dtype=float)
    Xi=[]
    count=0
    
    for x,y in tuplas:
        Q[count,0]=y
        Xi.append(x)
        count=count+1

    for x in range(len(Xi)-1):
        for y in range(len(Xi)-x-1):
            i=x+y+1
            j=x+1
            Q[i,j]=((X-Xi[i-j])*Q[i,j-1]-(X-Xi[i])*Q[i-1,j-1])/(Xi[i]-Xi[i-j])
    return Q






##ESTE NO SIRVE!
#puntos de la forma [(x_0,y_0,y'0),(x_1,y_1,y'1)...(x_n,y_n,y'n)], donde y'i es la derivada de f evaluada en xi
def hermiteSolucionesNOSIRVETODAVIA(puntosConDerivadas):
    #nuestra meta es resolver matrix*resultado = listaDeCoeficientes
    n = len(puntosConDerivadas) - 1
    matriz = matrix(2*(n+1))
    incognitas = []
    listaDeCoeficientes = []
    #agregar cada yi en el vector de coeficientes
    for yi in map(lambda puntosConDerivadaz:puntosConDerivadaz[1],puntosConDerivadas):
        listaDeCoeficientes.append(yi)
    #agregar cada derivada y'_i al vector de coeficientes
    for yprima_i in map(lambda puntosConDerivadaz:puntosConDerivadaz[2],puntosConDerivadas):
        listaDeCoeficientes.append(yprima_i)
    
    filasHechas = 0
    fila = []
    fila2 = []
    for xi in map(lambda puntosConDerivadaz:puntosConDerivadaz[0],puntosConDerivadas):
        fila = []
        fila2 = []
        print "e"
        for i in reversed(range(2*(n+1))):
            fila.append(xi**(i))
            fila2.append((i)*xi**(2*(i-1)))
        
        print "fila2 ",fila2
        print "fila ", fila
        print "filas hechas ",filasHechas
        matriz.set_row(filasHechas,fila)
        matriz.set_row(filasHechas+1,fila2)
        filasHechas = filasHechas+2
    
    vectorDeResultados = matriz.solve_right(vector(listaDeCoeficientes))
    return vectorDeResultados

#dada una lista = [x_1,x_n], devuelve multiplicatoria de i hasta n de x_i 
def multiplicatoriaDe(lista): 
    return reduce(lambda x,y: x*y, lista) 
def sumatoriaDe(lista): 

    return reduce(lambda x,y: x+y, lista) 
#mandar puntos de la forma: 
#puntos = [(1,2),(4,3),(5,5)] 
def polinomioDeLagrange(puntos): 
    terminosDeSumatoria = [] 
    for x_k,y_k in puntos: 
        terminosDeMultiplicatoria = [] 
        for x_i,y_i in puntos: 
            if x_k!=x_i: 
                terminosDeMultiplicatoria.append((x-x_i)/(x_k-x_i)) 
        multiplicatoria = y_k*multiplicatoriaDe(terminosDeMultiplicatoria) 
        terminosDeSumatoria.append(multiplicatoria) 
    polinomioLagrange(x) = sumatoriaDe(terminosDeSumatoria) 
    return polinomioLagrange 

