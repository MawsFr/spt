package nezzari.mustapha.question4.projet.controller;

import javax.swing.AbstractListModel;

import nezzari.mustapha.question4.projet.model.Quizz;
import nezzari.mustapha.question4.projet.model.QuizzManager;

public class QuizzListModel extends AbstractListModel<Quizz> {

	private static final long serialVersionUID = -6819194245784245185L;
	
	protected QuizzManager quizzManger;
	
	public QuizzListModel(QuizzManager quizzManager) {
		this.quizzManger = quizzManager;
	}
	
	@Override
	public Quizz getElementAt(int index) {
		return quizzManger.getQuizzList().get(index);
	}
	
	@Override
	public int getSize() {
		return quizzManger.getQuizzList().size();
	}

}
