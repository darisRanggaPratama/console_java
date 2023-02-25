package MVC2;

class Controllers {
    private Models models;
    private Views views;

    public Controllers(Models models, Views views) {
        this.models = models;
        this.views = views;
    }

    public void updateData() {
        String[] data = views.getUserInput();
        models.setData(data);
    }

    public void displayData() {
        String[] data = models.getData();
        views.displayData(data);
    }
}
