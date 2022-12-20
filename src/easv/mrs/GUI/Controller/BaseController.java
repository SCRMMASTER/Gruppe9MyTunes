package easv.mrs.GUI.Controller;

import easv.mrs.GUI.Model.MRSModel;

//All Controllers extend this Base Controller.
public abstract class BaseController {

    private MRSModel model;

    public void setModel(MRSModel model) {
        this.model = model;
    }

    public MRSModel getModel() {
        return model;
    }

    public abstract void setup();
}
