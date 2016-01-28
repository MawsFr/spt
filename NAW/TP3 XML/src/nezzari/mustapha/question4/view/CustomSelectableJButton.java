package nezzari.mustapha.question4.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

public class CustomSelectableJButton extends CustomJButton {

	private static final long serialVersionUID = 7307577214057908291L;
	
	protected boolean isSelected;
	
	public CustomSelectableJButton(AbstractAction action) {
		super(action);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isSelected = !isSelected;
				
			}
		});
		
	}
	
	public boolean selected() {
		return this.isSelected;
	}

	public void select(boolean selected) {
		this.isSelected = selected;
	}
	
	
	
	
	
}
