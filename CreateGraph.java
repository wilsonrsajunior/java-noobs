class CreateGraph{
	/*
	 * Catarina, 92192
	 * Exercicio 1
	 */
	
	//Função auxiliar para criar a coluna
	private static ColorImage createColumn (int width, int height, Color color){
		ColorImage column = new ColorImage(width, height);
		// os ciclos vão servir para pintar a coluna
			for(int i = 0; i < column.getWidth(); i++)
				for(int j = 0; j < column.getHeight(); j++){
					column.setColor(i,j,color);
				}
		return column;
	}

	//Função auxiliar para descobrir o valor máximo dentro do vetor
	private static int maximumValue (int [] v){
		int maximum = 0;
		for (int i = 0; i < v.length; i++){
			if (v[i]> maximum)
				maximum = v[i];
		}	
		return maximum;
	}
	
	// Função principal para criar o gráfico pedido no exercicio
	static ColorImage graph2d (int [] v, int width, int space, Color color){
		if(width <= 0 || space < 0 ) 
			throw new IllegalArgumentException("Insert positve numbers only!");
		/*criar uma imagem com largura igual ao numero de valores dentro do vetor a multiplicar por o espaco 
		 * e pela largura das colunas e adicionar ainda o espaço, uma vex que vamos precisar de um espaco no inicio e no fim da imagem
		 * e comprimento dado pelo o valor maximo dentro do vetor
		*/
		ColorImage graph = new ColorImage (v.length*width+v.length*space+space, maximumValue(v)); 
		for(int i = 0; i < v.length; i++){ // vamos percorrer o vetor para criarmos uma coluna em cada valor novo dentro do vetor
			if(v[i] < 0)
				throw new IllegalArgumentException("Vector values need to be positive!");
			else if(v[i] > 0) {
    			ColorImage columnAux = createColumn(width, v[i], color);
    				for(int a = 0; a < columnAux.getWidth(); a++) {
    					for(int j = 0; j < columnAux.getHeight(); j++){
    						graph.setColor(a+space+i*space+i*columnAux.getWidth(), graph.getHeight()-1-j, 
    								columnAux.getColor(i,columnAux.getHeight()-1-j)); 
    					}
    				}
			}
		}
		return graph;
	}
	
	/*
	 *  Exercicio 2
	*/
	
	//Função auxiliar para devolver a diferenca entre a cor actual e a cor dada
	private static Color media(ColorImage column){
		int r = 0;
		int g = 0;
		int b = 0;
		int count = 0;
		for(int i = 0; i < column.getWidth(); i++){
			for(int j = 0; j < column.getHeight(); j++){
				r += column.getColor(i,j).getR();
				g += column.getColor(i,j).getG();
				b += column.getColor(i,j).getB();
				count ++;
			}
		}
		return new Color (r/count,g/count,b/count);
	}

	// Procedimento para adicionar contorno a imagem
	private static void border(ColorImage column, Color c){
			for(int i = 0; i < column.getWidth(); i++){
				column.setColor(i,0,c);
				for(int j = 0; j < column.getHeight(); j++){
					column.setColor(column.getWidth()-1,j,c);
					column.setColor(0,j,c);
			}
		}
	}

	
	//Procedimento pinta a coluna de forma gradiente
	private static void gradientColumn(ColorImage column, int pixels, Color color){
        Color aux = new Color (color.getR(),color.getG(), color.getB());
        // enquanto o i for inferior aos pixels vamos criar uma nova cor cada vez mais clara e pintar a coluna
        for(int i = 0; i < pixels; i++){
                aux = media(column);
            if (i == 0)
            	border(column,aux);  
            for(int j = i; j < column.getHeight() ; j++){
            	column.setColor(i,j,aux);
                column.setColor(column.getWidth()-1-i,j,aux);
                }
            for(int k = i; k < column.getWidth()-i; k++){
                column.setColor(k,i,aux);
            }
        }
	}
	
	//Função principal devolve o grafico com contornos suavizados
	static ColorImage gradientGraph (int [] v, int width, int space, int pixels, Color color){
		if(color == null)
			throw new IllegalArgumentException("Color doesn't exist, please insert a valid color");
		if(width < 0 || space < 0 || pixels < 0)
			throw new IllegalArgumentException("Insert positve numbers only!");
		if(pixels > width || pixels > maximumValue(v)){
			throw new IllegalArgumentException("Pixels need to be lower than width and height!");
		}
		ColorImage graph = new ColorImage (v.length*width+v.length*space+space, maximumValue(v));
		for(int i = 0; i < v.length; i++){
			if(v[i] < 0)
				throw new IllegalArgumentException("Vector values need to be positive!");
			else if(v[i] > 0) {
    			ColorImage columnAux = createColumn(width, v[i], color);
    			gradientColumn(columnAux, pixels, color);
    			for(int a = 0; a < columnAux.getWidth(); a++){	
    				for(int j = 0; j < columnAux.getHeight(); j++){
    					graph.setColor(a+space+i*space+i*columnAux.getWidth(), graph.getHeight()-1-j, 
    							columnAux.getColor(a, columnAux.getHeight()-1-j)); 
    				}
    			}
			}
		} 
		return graph;
	} 
		
	/*
	 * Exercicio 3
	 */
	
	// Procedimento auxiliar para criar um circulo
	private static void createCircle(ColorImage circle, int raio, Color c){
		int x = raio;
		for(int i = 0; i < circle.getWidth() ; i++){
			for(int j = 0; j < circle.getHeight(); j++){
				if(((i-x)*(i-x)+(j-x)*(j-x)) <= raio*raio){
					circle.setColor(i,j,c);
				}
			}
		}
	}
	
	//Funçao principal para criar um grafico
	static ColorImage dispersion(int [] v, int raio, int space, Color c){
		if(c == null)
			throw new IllegalArgumentException("Color doesn't exist, please insert a valid color");
		if(raio <= 0 || space < 0)
			throw new IllegalArgumentException("O raio e o espaco tem de ser maiores que 0!");
		/* A imagem vai ter de ter largura igaul ao numero de elemntos dentro do vetor a multiplicar pelo diametro e pelo espaco 
		 * adicionando-se ainda o espaco por precisar de um espaco no incio e fim e comprimento igual ao valor maximo no vetor
		 */
		ColorImage graph = new ColorImage(v.length * 2 * raio+ v.length * space + space,
											maximumValue(v));
		//Criar a base para criar o circulo
		ColorImage aux = new ColorImage(2 * raio+1 , 2 * raio+1); 
		createCircle(aux, raio, c);
		for(int i = 0; i < v.length; i++){
			if(v[i] < 0)
				throw new IllegalArgumentException("Vector values need to be positive!");
			for(int j = 0; j < aux.getWidth(); j++){
				for(int k = 0; k < aux.getHeight(); k++){
					// Tenho de assegurar que caso saia dos limites da imagem so pinta x parte do circulo
					if(graph.getHeight() - v[i] - raio + k < 0)
						graph.setColor(j+space+i*space+i*2*raio,0, aux.getColor(j,k));
					else if(graph.getHeight() - v[i] - raio + k < graph.getHeight())
						graph.setColor(j+space+i * space + i*2 * raio,
								graph.getHeight() - v[i] - raio + k,aux.getColor(j,k));
 
				}
			}
		}
		return graph;
	}
	
	/*
	 * Exercicio 4
	 * Grafico 90 graus
	 */
	
	static ColorImage graph90degrees(ColorImage graph){
		if(graph == null)
			throw new IllegalArgumentException("Graph doesn't exist, please use a valid graph");
		ColorImage graphAux = new ColorImage(graph.getHeight(),graph.getWidth());
		for(int i = 0; i< graph.getWidth(); i++){
			for(int j = 0; j < graph.getHeight(); j++){
				graphAux.setColor(graphAux.getWidth()-1-i,j,graph.getColor(j, i));
			}
		}
		return graphAux;
	}
	
}