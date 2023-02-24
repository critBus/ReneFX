/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.FX;

import de.jensd.fx.glyphs.emojione.EmojiOneView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author Rene
 */
public class TransitionsFill extends Transition {
    
    private Color start;
    private Color end;
    private CacheHint oldCacheHint = CacheHint.DEFAULT;
    private boolean oldCache = false;

   
    /**
     * Version 0.1
     */
    private ObjectProperty<Node> valor;
    
    public final void setValor(Node value) {
        if ((valor != null) || (value != null /* DEFAULT_SHAPE */)) {
            valorProperty().set(value);
        }
    }
    
    public final Node getValor() {
        return (valor == null) ? null : valor.get();
    }
    
    public final ObjectProperty<Node> valorProperty() {
        if (valor == null) {
            valor = new SimpleObjectProperty<>(this, "region", null);
        }
        return valor;
    }

    /**
     * The duration of this {@code JFXFillTransition}.
     * <p>
     * Note: While the unit of {@code duration} is a millisecond, the
     * granularity depends on the underlying operating system and will in
     * general be larger. For example animations on desktop systems usually run
     * with a maximum of 60fps which gives a granularity of ~17 ms.
     * <p>
     * Setting duration to value lower than {@link Duration#ZERO} will result in
     * {@link IllegalArgumentException}.
     *
     * @defaultValue 400ms
     */
    private ObjectProperty<Duration> duration;
    private static final Duration DEFAULT_DURATION = Duration.millis(400);
    
    public final void setDuration(Duration value) {
        if ((duration != null) || (!DEFAULT_DURATION.equals(value))) {
            durationProperty().set(value);
        }
    }
    
    public final Duration getDuration() {
        return (duration == null) ? DEFAULT_DURATION : duration.get();
    }
    
    public final ObjectProperty<Duration> durationProperty() {
        if (duration == null) {
            duration = new ObjectPropertyBase<Duration>(DEFAULT_DURATION) {
                
                @Override
                public void invalidated() {
                    try {
                        setCycleDuration(getDuration());
                    } catch (IllegalArgumentException e) {
                        if (isBound()) {
                            unbind();
                        }
                        set(getCycleDuration());
                        throw e;
                    }
                }
                
                @Override
                public Object getBean() {
                    return this;
                }
                
                @Override
                public String getName() {
                    return "duration";
                }
            };
        }
        return duration;
    }

    /**
     * Specifies the start color value for this {@code JFXFillTransition}.
     *
     * @defaultValue {@code null}
     */
    private ObjectProperty<Color> fromValue;
    private static final Color DEFAULT_FROM_VALUE = null;
    
    public final void setFromValue(Color value) {
        if ((fromValue != null) || (value != null /* DEFAULT_FROM_VALUE */)) {
            fromValueProperty().set(value);
        }
    }
    
    public final Color getFromValue() {
        return (fromValue == null) ? DEFAULT_FROM_VALUE : fromValue.get();
    }
    
    public final ObjectProperty<Color> fromValueProperty() {
        if (fromValue == null) {
            fromValue = new SimpleObjectProperty<>(this, "fromValue", DEFAULT_FROM_VALUE);
        }
        return fromValue;
    }

    /**
     * Specifies the stop color value for this {@code JFXFillTransition}.
     *
     * @defaultValue {@code null}
     */
    private ObjectProperty<Color> toValue;
    private static final Color DEFAULT_TO_VALUE = null;
    
    public final void setToValue(Color value) {
        if ((toValue != null) || (value != null /* DEFAULT_TO_VALUE */)) {
            toValueProperty().set(value);
        }
    }
    
    public final Color getToValue() {
        return (toValue == null) ? DEFAULT_TO_VALUE : toValue.get();
    }
    
    public final ObjectProperty<Color> toValueProperty() {
        if (toValue == null) {
            toValue = new SimpleObjectProperty<>(this, "toValue", DEFAULT_TO_VALUE);
        }
        return toValue;
    }

    /**
     * The constructor of {@code JFXFillTransition}
     *
     * @param duration The duration of the {@code JFXFillTransition}
     * @param valor The {@code region} which filling will be animated
     * @param fromValue The start value of the color-animation
     * @param toValue The end value of the color-animation
     */
    public TransitionsFill(Duration duration, Node valor, Color fromValue,
            Color toValue) {
        setDuration(duration);
        setValor(valor);
//        setRegion(region);
        setFromValue(fromValue);
        setToValue(toValue);
        setCycleDuration(duration);
        statusProperty().addListener(new ChangeListener<Animation.Status>() {
            @Override
            public void changed(ObservableValue<? extends Animation.Status> ov, Animation.Status t, Animation.Status newStatus) {
                switch (newStatus) {
                    case RUNNING:
                        starting();
                        break;
                    default:
                        stopping();
                        break;
                }
            }
        });
    }

    /**
     * The constructor of {@code JFXFillTransition}
     *
     * @param duration The duration of the {@code JFXFillTransition}
     * @param fromValue The start value of the color-animation
     * @param toValue The end value of the color-animation
     */
    public TransitionsFill(Duration duration, Color fromValue, Color toValue) {
        this(duration, null, fromValue, toValue);
    }

    /**
     * The constructor of {@code JFXFillTransition}
     *
     * @param duration The duration of the {@code JFXFillTransition}
     * @param valor The {@code region} which filling will be animated
     */
    public TransitionsFill(Duration duration, Node valor) {
        this(duration, valor, null, null);
    }

    /**
     * The constructor of {@code JFXFillTransition}
     *
     * @param duration The duration of the {@code FadeTransition}
     */
    public TransitionsFill(Duration duration) {
        this(duration, null, null, null);
    }

    /**
     * The constructor of {@code JFXFillTransition}
     */
    public TransitionsFill() {
        this(DEFAULT_DURATION, null);
    }

    /**
     * Called when the animation is starting
     */
//    private CornerRadii radii;
//    private Insets insets;
    protected void starting() {
        // init animation values
        if (start == null) {
            oldCache = valor.get().isCache();
            oldCacheHint = valor.get().getCacheHint();

            //Background b=(Background)valor.get().getFill();
//            radii = valor.get().getBackground() == null ? null : valor.get()
//                .getBackground()
//                .getFills()
//                .get(0)
//                .getRadii();
//            insets = valor.get().getBackground() == null ? null : valor.get()
//                .getBackground()
//                .getFills()
//                .get(0)
//                .getInsets();
            start = fromValue.get();
            end = toValue.get();
            valor.get().setCache(true);
            valor.get().setCacheHint(CacheHint.SPEED);
        }
    }

    /**
     * Called when the animation is stopping
     */
    protected void stopping() {
        valor.get().setCache(oldCache);
        valor.get().setCacheHint(oldCacheHint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void interpolate(double frac) {
        if (start == null) {
            starting();
        }
        Color newColor = start.interpolate(end, frac);
        if (Color.TRANSPARENT.equals(start)) {
            newColor = new Color(end.getRed(), end.getGreen(), end.getBlue(), newColor.getOpacity());
        }
        Node n = valor.get();//FontAwesomeIconView 
//        System.out.println("cambiar");
        if (n instanceof Shape) {
//            System.out.println("newColor="+newColor);
            ((Shape) n).setFill(newColor);
            return;
        }
       
//        valor.get().setFill(newColor);
        //   valor.get().setBackground(new Background(new BackgroundFill(newColor, radii, insets)));
    }
}
