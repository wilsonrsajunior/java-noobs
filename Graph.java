class Graph {
	/*
	 * Catarina, 92192
	 */
	
	ColorImage graph;
	String graphName;
	String x;
	String y;
	
	Graph (ColorImage graph){
		if(graph == null)
			throw new IllegalArgumentException ("Graph doesn't exist, please use a valid graph");
		this.graph = graph;
        graphName = "";
        x = "";
        y = "";
	}
	
	Graph(ColorImage graph, String graphName, String x, String y){
		if(graph == null)
			throw new IllegalArgumentException ("Graph doesn't exist, please use a valid graph");
		this.graph = graph;
		this.graphName = graphName;
		this.x = x;
		this.y = y;
	}
	
	void changeGraphName (String graphName){ 
		this.graphName = graphName;
	}
	
	void changeX (String x){
		this.x = x;
	}
	
	void changeY (String y){
		this.y = y;
	}
	
	void transparency(){
		for(int i = 0; i <graph.getWidth(); i++){
			for(int j = 0; j < graph.getHeight(); j++){
				if(j % 2 == 0 && i % 2 != 0 || i % 2 == 0 && j % 2 != 0)
					graph.setColor(i,j, new Color (0,0,0));
			}
		}
	}
	
	 ColorImage getImage(){
		transparency();
		return graph;
	}
	
	String getGraphName(){
		return this.graphName;
	}
	
	String getX(){
		return this.x;
	}
	
	String getY(){
		return this.y;
	}
	
	String getGraphInfo(){
		return "Titulo: " + graphName + " Titulo das abcissas: " + x + 
				" Titulo das ordenadas: " + y;
	}
}