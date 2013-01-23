package controler;

/**
 *
 * @author gaelvarlet
 */
public class ColorAgenda {
    private String name;
    private String typeStyle;

    public ColorAgenda() {
    }

    
    
    public ColorAgenda(String name, String pathPicture) {
        this.name = name;
        this.typeStyle = pathPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeStyle() {
        return typeStyle;
    }

    public void setTypeStyle(String pathPicture) {
        this.typeStyle = pathPicture;
    }

    @Override
    public String toString() {
        return "ColorEvent{" + "name=" + name + ", typeStyle=" + typeStyle + '}';
    }
}
