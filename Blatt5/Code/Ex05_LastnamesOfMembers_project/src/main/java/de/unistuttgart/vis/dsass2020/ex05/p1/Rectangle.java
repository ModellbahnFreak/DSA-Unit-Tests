package de.unistuttgart.vis.dsass2020.ex05.p1;

import java.util.Collection;

/**
 * This class represents a quadrilateral, where the angles are 90 degrees.
 */
public class Rectangle {

    /**
     * Returns whether the given rectangle is fully contained in this rectangle or has the same size.
     * <p>
     * Note: If {@code this.equals(rectangle)} is {@code true}, this will also be {@code true}.
     *
     * @param rectangle The other rectangle to be checked with.
     * @return true iff the given rectangle is fully contained in this one or has the same size
     */
    public boolean fullyContaines(final Rectangle rectangle) {
        if (rectangle == null) {
            throw new IllegalArgumentException("The rectangle to compare to can't be null");
        }
        return this.equals(rectangle) ||
                (
                        (rectangle.x >= this.x) && rectangle.y >= this.y &&
                                (rectangle.x <= (this.x + this.width)) && (rectangle.y <= (this.y + this.height)) &&
                                ((rectangle.x + rectangle.width) >= this.x) && ((rectangle.y + rectangle.height) >= this.y) &&
                                ((rectangle.x + rectangle.width) <= this.x + this.width) &&
                                ((rectangle.y + rectangle.height) <= this.y + this.height)
                );
    }

}
