package org.vaadin.touchmenu;


import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractComponent;

import com.google.common.collect.Lists;
import org.vaadin.touchmenu.client.button.TouchMenuButtonRpc;
import org.vaadin.touchmenu.client.button.TouchMenuButtonState;

import java.util.List;
import java.util.UUID;

/**
 * A button to be used with the {@link TouchMenu} component.
 *
 * @author Mikael Grankvist - Vaadin }>
 */
public class TouchMenuButton extends AbstractComponent {

    private final TouchMenuButtonRpc rpc = new TouchMenuButtonRpc() {

        @Override
        public void buttonClicked() {
            for (ButtonListener listener : listeners) {
                listener.buttonClicked(TouchMenuButton.this);
            }
        }
    };

    private List<ButtonListener> listeners = Lists.newLinkedList();

    public TouchMenuButton(String caption) {
        super();

        setId(UUID.randomUUID().toString());

        registerRpc(rpc);
        setCaption(caption);
        setWidth(50, Unit.PIXELS);
        setHeight(50, Unit.PIXELS);
    }

    public TouchMenuButton(String caption, Resource icon) {
        this(caption);
        setIcon(icon);
    }

    @Override
    protected TouchMenuButtonState getState() {
        return (TouchMenuButtonState) super.getState();
    }

    /**
     * Add a new buttonClickedListener
     */
    public boolean addButtonClickedListener(ButtonListener listener) {
        return listeners.add(listener);
    }

    /**
     * Remove buttonClickedListener
     */
    public boolean removeButtonClickedListener(ButtonListener listener) {
        return listeners.remove(listener);
    }

    /**
     * Button click listener
     */
    public interface ButtonListener {
        /**
         * Called when a {@link TouchMenuButton} has been clicked. A reference to the
         * {@link TouchMenuButton} is returned.
         *
         * @param button {@link TouchMenuButton} that has been clicked.
         */
        void buttonClicked(TouchMenuButton button);
    }
}
