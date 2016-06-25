package co.uk.dragosolutions.components;

import com.google.common.base.MoreObjects;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;

@Named("component")
public class MyComponent {

    @Value("${environment.name}")
    private String name;


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
