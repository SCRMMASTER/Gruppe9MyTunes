package easv.mrs.GUI.Controller;

import easv.mrs.GUI.Model.MyTunesModel;

//All Controllers extend this Base Controller.
public abstract class BaseController {

    private MyTunesModel model;

    public void setModel(MyTunesModel model) {
        this.model = model;
    }

    public MyTunesModel getModel() {
        return model;
    }

    public abstract void setup();
}
