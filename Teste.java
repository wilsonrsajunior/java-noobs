class Teste {
	/*
	 * Catarina , 92192
	 */
	
	static void createGraph(int [] v, int width, int space){
		Color c1 = new Color (0,255,0);CreateGraph.graph2d(v, width, space, c1);
	}
	
	static void testGradient(int [] v, int width, int space, int pixel){
		Color c = new Color(0,255,0);CreateGraph.gradientGraph(v,width,space,pixel,c);
	}
	
	static void dispersiontest (int [] v, int raio, int space){
		Color c = new Color(255,0,0);
		CreateGraph.dispersion(v,raio,space,c);
	}
	
	static void graph90degreesTest(int [] v, int width, int space, int pixel){
		Color c = new Color(255,0,0);
		ColorImage s = CreateGraph.graph90degrees(CreateGraph.gradientGraph(v,width,space,pixel,c));
	}
	
	static void test(int [] v, int raio, int space){
		Color c = new Color (200,103,130);
		ColorImage graph =CreateGraph.dispersion(v,raio,space,c);
		Graph s = new Graph(graph);
		s.transparency();
	}
	
	static void removeTest(int [] v, int raio, int space,int pixel){
		Color c = new Color (200,103,130);
	    ColorImage graph = CreateGraph.dispersion(v,raio,space,c);
	    ColorImage graph2 = CreateGraph.gradientGraph(v,raio,space,pixel,c);
	    Graph s = new Graph(graph);
	    Graph h = new Graph(graph2);
	    Graph [] l ={s,h,null,null,null};
	    Overlay u = new Overlay(l);
		u.remove();
		u.getTop(); // feito para vermos o ultimo passo do remove
	}

	static void topAddTest(int [] v, int raio, int space,int pixel){
		Color c = new Color (200,103,130);
	    ColorImage graph = CreateGraph.dispersion(v,raio,space,c);
	    ColorImage graph2 = CreateGraph.gradientGraph(v,raio,space,pixel,c);
	    Graph s = new Graph(graph);
	    Graph h = new Graph(graph2);
	    Graph [] l ={s,h,null,null,null};
	    Overlay u = new Overlay(l);
		ColorImage graph3 = CreateGraph.graph2d(v,raio,space,c);
		u.topAdd(new Graph(graph3)); // feito para vermos o ultimo passo do topAdd
	}

	static void addToXPositionTest(int [] v, int raio, int space,int pixel){
		Color c = new Color (200,103,130);
	    ColorImage graph = CreateGraph.dispersion(v,raio,space,c);
	    ColorImage graph2 = CreateGraph.gradientGraph(v,raio,space,pixel,c);
	    Graph s = new Graph(graph);
	    Graph h = new Graph(graph2);
	    Graph [] l ={s,h,null,null,null};
	    Overlay u = new Overlay(l);
	    ColorImage graph3 = CreateGraph.graph2d(v,raio,space,c);
		u.addToXPosition(0,new Graph(graph3));
		u.topAdd(s);
		u.getTop(); // feito para vermos o ultimo passo do addToXPosition
	}

	static void changePositionsTest(int [] v, int raio, int space,int pixel){
		Color c = new Color (200,103,130);
	    ColorImage graph = CreateGraph.dispersion(v,raio,space,c);
	    ColorImage graph2 = CreateGraph.gradientGraph(v,raio,space,pixel,c);
	    Graph s = new Graph(graph);
	    Graph h = new Graph(graph2);
	    Graph [] l ={s,h,null,null,null};
	    Overlay u = new Overlay(l);
		u.changePositions(0, 1);
		u.getTop(); // feito para vermos o ultimo passo do changePositions
	}

	static void noTitleTest(int [] v, int raio, int space,int pixel){
		Color c = new Color (200,103,130);
	    ColorImage graph = CreateGraph.dispersion(v,raio,space,c);
	    ColorImage graph2 = CreateGraph.gradientGraph(v,raio,space,pixel,c);
	    Graph s = new Graph(graph, "Titulo", "X", "Y");
	    Graph h = new Graph(graph2);
		ColorImage graph3 = CreateGraph.graph2d(v,raio,space,c);
		Graph z = new Graph(graph3);
	    Graph [] l ={s,h,z,null,null};
	    Overlay u = new Overlay(l);
	    u.noTitle();
	}

	static void sortTest(int [] v, int raio, int space,int pixel){
	    Color c = new Color (200,103,130);
	    ColorImage graph = CreateGraph.dispersion(v,raio,space,c);
	    ColorImage graph2 = CreateGraph.gradientGraph(v,raio,space,pixel,c);
	    Graph s = new Graph(graph, "Titulo", "X", "Y");
	    Graph h = new Graph(graph2);
		ColorImage graph3 = CreateGraph.graph2d(v,raio,space,c);
		Graph z = new Graph(graph3, "Test", "X2", "Y2");
	    Graph [] l ={s,h,z,null,null};
	    Overlay u = new Overlay(l);	
		u.sort();
	}

	static void overlayTest(int [] v, int raio, int space,int pixel){
		Color c = new Color (200,103,130);
	    ColorImage graph = CreateGraph.dispersion(v,raio,space,c);
	    ColorImage graph2 = CreateGraph.gradientGraph(v,raio,space,pixel,c);
	    Graph s = new Graph(graph);
	    Graph h = new Graph(graph2);
	    Graph [] l ={s,h,null,null,null};
	    Overlay u = new Overlay(l);
		u.overlayRotate();
	}
}