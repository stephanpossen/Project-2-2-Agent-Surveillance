package Percept.Vision;

import Geometry.Angle;
import Geometry.Distance;
import Geometry.Point;

public class SentryFieldView extends FieldOfView {
    private Distance rangeMin;

    public SentryFieldView(Distance rangeMax, Distance rangeMin, Angle viewAngle) {
        super(rangeMax, viewAngle);
        this.rangeMin = rangeMin;
    }

    private boolean isInRange(Point point) {
        return rangeMin.getValue()<= point.getDistanceFromOrigin().getValue() && point.getDistanceFromOrigin().getValue() <= getRange().getValue();
    }


}