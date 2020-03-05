package io.openliberty.guides.inventory.model;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class XKCDObj {
    public String img;

    public XKCDObj() {

    }

    @JsonbCreator
    public XKCDObj(
      @JsonbProperty("img") String img) {
      this.img = img;
    }

    @Override
    public String toString() {
      return img;
    }
}