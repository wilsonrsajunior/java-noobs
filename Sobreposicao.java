class Overlay {
	
	private final static int TAMANHO_VETOR = 25;
	private Graph [] graphVec;
	private int next;
	
	Overlay(Graph graph){
		if(graph == null)
			throw new IllegalArgumentException("Graph is null.");
		graphVec = new Graph[TAMANHO_VETOR];
		graphVec[0] = graph;
		//next aponta para a primeira posicao que esta vazia
		next = 1; 
	}
	
	Overlay(Graph [] graphVec){
		if(graphVec == null)
			throw new IllegalArgumentException("Graph's pile doesn't exist.");
		this.graphVec = new Graph [graphVec.length];
		next = 0;
		for(int i = 0; i < graphVec.length; i++){
			if(graphVec[i] != null){
				this.graphVec[next] = graphVec[i];
				next++;
			}
		}
	}
	
	void topAdd(Graph graph){
		if(next == graphVec.length)
			throw new IllegalStateException ("Array of Graphs is full!");
		graphVec[next] = graph;
		next ++;
	} 

	void remove (){
		if (next == 0)
			throw new IllegalStateException ("Array of Graphs is empty!");
		// next -1 porque comeca na ultima posicao que tem um grafico e vai ate a primeira
		graphVec[next-1] = null;
		next--;
	}
	
	Graph getTop(){
		if(next == 0)
			throw new IllegalStateException("Array is empty!");
		return graphVec[next-1];
	}
	
	void addToXPosition(int x, Graph graph){
		if(next == graphVec.length)
			throw new IllegalStateException ("Array of Graphs is full!");
		Graph aux = graphVec[x];
		graphVec[x] = graph;
		for(int i = next; x < i; i--){
			if(i != x+1)
				graphVec[i] = graphVec[i-1];
			else
				graphVec[i] = aux;
		}
		next++;
	}
	
	void changePositions(int x, int y){
		if ( graphVec[x] == null || graphVec[y] == null)
			throw new IllegalArgumentException ("Null graphs can't be swapped!");
		if(x < 0 || y < 0)
			throw new IllegalArgumentException ("Only positive numbers allowed!");
		if(x > graphVec.length || y > graphVec.length)
			throw new IllegalArgumentException ("Values can't be bigger than array!");
			Graph aux = graphVec[y];
			graphVec[y] = graphVec[x];
			graphVec[x] = aux;
	}
	
	Graph [] noTitle (){
		Graph [] aux = new Graph[graphVec.length];
		int i = 0;
		for( int a = 0; a < aux.length; a++){
			if(graphVec[a].getGraphName().equals("")){
				aux[i] = graphVec[a];
				i++;
			}
		}
		return aux;
	}
	
	Graph [] sort(){
        Graph [] aux = new Graph [next];
        for(int a = 0; a < aux.length; a++){
            aux[a] = graphVec[a];
        }
        for (int i = 0; i < aux.length; i++){
            for(int j = 0 ; j < aux.length-i-1; j++){
            	if(aux[j].getGraphName().compareTo(aux[j+i].getGraphName()) > 0
            			|| aux[j].getGraphName().equals("")){
                	Graph aux1 = aux[j];
                	aux[j] = aux[j + i];
                	aux[j + i] = aux1;
            	}
            }
        }
        return aux;
     }
	
	ColorImage overlay(){
		int maxWidth = 0; 
		int maxHeight = 0;
		for(int a = 0; a < next; a++){
			if(graphVec[a].getImage().getWidth() > maxWidth)
				maxWidth = graphVec[a].getImage().getWidth();
			if(graphVec[a].getImage().getHeight() > maxHeight)
				maxHeight = graphVec[a].getImage().getHeight();
		}
		ColorImage aux = new ColorImage (maxWidth,maxHeight);
		for(int b = 0; b < next; b++){
			for(int i = 0; i < graphVec[b].getImage().getWidth(); i++){
				for(int j = 0; j < graphVec[b].getImage().getHeight(); j++){
					aux.setColor(i,j,graphVec[b].getImage().getColor(i,j));
				}
			}
			
		} 
		return aux;
	}
	
	ColorImage overlayRotate(){
		return CreateGraph.graph90degrees(overlay());
	}	
}
