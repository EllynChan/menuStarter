package model;

public class ShopKeeper {
    private String name;
    private String dialog;

    public ShopKeeper(String name) {
        this.name = name;
        this.dialog = "Well, I'm " + name + ". You can do whatever you want in my shop";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.dialog = "Well, I'm " + name + ". You can do whatever you want in my shop";
    }

    public void setDialog(String newDialog) {
        this.dialog = newDialog;
    }

    public String getDialog() {
        return dialog;
    }


}
