class EditorImagem{
    final static int SIZE = 500;

    ColorImage [] versions; // Array de versões
    int current_version = 0;
    int versions_created = 0; 

    EditorImagem(ColorImage image) {
        versions = new ColorImage[SIZE];
        versions[0] = image;
        current_version = 1;
        versions_created = 1;
    }

    public ColorImage RollbackEffect() {
        if (current_version <= 0) throw new IllegalArgumentException("Erro1");

        ColorImage image = versions[current_version];
        current_version--;

        return image;
    }

    public ColorImage ForwardEffect() {
        if (current_version >= versions_created) throw new IllegalArgumentException("não podes fazer forward para um index q n existe");
        
        ColorImage image = versions[current_version+1];
        current_version++;

        return image;
    }
    
}