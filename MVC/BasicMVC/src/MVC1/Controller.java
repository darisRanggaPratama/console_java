package MVC1;

class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void updateName() {
        String name = view.getUserInput();
        model.setName(name);
    }

    public void displayName() {
        String name = model.getName();
        view.displayName(name);
    }
}

