package engine;

import java.util.StringTokenizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI{
	final static int borderY = 30;
	final static int borderX = 50;
	final static int windowHeight = 600;
	final static int windowWidth = 600;

	private TextArea codeEntry;
	private TextArea[][] feedback;
	private TextArea gameStatus;
	private JFrame W;
	private Board board;

	public GUI() {
		W = new JFrame();

		board = new Board();

		createTextFields(W);
		createButtons(W);
		W.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		W.setBounds(100,100,windowWidth,windowHeight);
		W.setTitle("CodeBreaker");
		W.setLayout(null);
		W.setVisible(false);
	}

	public void open() {
		W.setVisible(true);
	}

	private void createTextFields(JFrame W) {
		//Create label and text area for where the code is to be entered.
		//Label.
		JLabel codeEntryTitle = new JLabel("Guess:");
		codeEntryTitle.setBounds(10 + borderX,20 + borderY,50,20);
		W.add(codeEntryTitle);

		//Text area for displaying the code.
		codeEntry = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
		codeEntry.setEditable(false);
		codeEntry.setBounds(60 + borderX, 20 + borderY, 60, 20);
		W.add(codeEntry);

		//Text area for displaying feedback from the guess.
		feedback = new TextArea[board.getGuessNum()][3];

		for(int i = 0; i < feedback.length; i++) {
			for(int j = 0; j < feedback[0].length; j++) {
				feedback[i][j] = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
				feedback[i][j].setEditable(false);
			}

			//Text area for correct position.
			feedback[i][0].setBounds(windowWidth/2, 30*(i + 1) + borderY, 20, 20);
			W.add(feedback[i][0]);

			//Text area for correct numbers.
			feedback[i][1].setBounds(windowWidth/2 + 60, 30*(i + 1) + borderY, 20, 20);
			W.add(feedback[i][1]);

			//Code.
			feedback[i][2].setBounds(windowWidth/2 + 60*2, 30*(i + 1) + borderY, 100, 20);
			W.add(feedback[i][2]);
		}

		//Text area for displaying game status.
		gameStatus = new TextArea("", 0, 0, TextArea.SCROLLBARS_NONE);
		gameStatus.setEditable(false);
		gameStatus.setBounds(windowWidth/2, 30*(1 + board.getGuessNum()) + borderY, 2*60 + 100, 20);
		W.add(gameStatus);
	}

	private void createButtons(JFrame W) {
		//Create the keypad for numbers 1-9.
		JButton[] keypad = new JButton[9];
		int index = 0;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				keypad[index] = new JButton(Integer.toString(index+1));
				keypad[index].setBounds(j*60 + borderX, (i+1)*60 + borderY, 60, 60);
				keypad[index].addActionListener(new KeypadActionListener(keypad[index]));
				W.add(keypad[index++]);
			}
		}

		//Add the zero button below the keypad.
		JButton zero = new JButton("0");
		zero.setBounds(60 + borderX,60*4 + borderY,60,60);
		zero.addActionListener(new KeypadActionListener(zero));
		W.add(zero);

		//Add the enter button.
		JButton enter = new JButton("Enter");
		enter.setBounds(2*60 + borderX, 6*60, 80, 40);
		enter.addActionListener(new EnterActionListener());
		W.add(enter);

		//Add the clear button.
		JButton clear = new JButton("Clear");
		clear.setBounds(borderX - 20, 6*60, 80, 40);
		clear.addActionListener(new ClearActionListener());
		W.add(clear);

		//New game button.
		JButton newGame = new JButton("New Game");
		newGame.setBounds(windowWidth/2 - 50, windowHeight - borderY - 60, 120, 40);
		newGame.addActionListener(new NewGameActionListener());
		W.add(newGame);
	}

	private class KeypadActionListener implements ActionListener {
		private JButton button;

		public KeypadActionListener (JButton button) {
			this.button = button;
		}

		public void actionPerformed(ActionEvent e) {
			if(codeEntry.getText().compareTo("Invalid") == 0) {
				codeEntry.setText("");
			}

			codeEntry.setText(codeEntry.getText() + button.getText());
		}
	}

	private class EnterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(board.validateGuess(codeEntry.getText())) {
				String result = board.getFeedback(codeEntry.getText());

				StringTokenizer resultToken = new StringTokenizer(result, ":");
				feedback[board.getGuessCount() - 1][0].setText(resultToken.nextToken());
				feedback[board.getGuessCount() - 1][1].setText(resultToken.nextToken());
				feedback[board.getGuessCount() - 1][2].setText(codeEntry.getText());

				if(resultToken.hasMoreTokens()) {
					int[] code = board.getCode();
					String codeString = "";

					for(int i = 0; i < code.length; i++) {
						codeString = codeString + code[i];
					}

					gameStatus.setText("Game Over. The code was: " + codeString);
				}

				if(board.checkGuess(codeEntry.getText())) {
					gameStatus.setText("You've cracked the code!");
				} 

				codeEntry.setText("");
			} else {
				codeEntry.setText("Invalid");
			}
		}
	}

	private class ClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			codeEntry.setText("");
		}
	}

	private class NewGameActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			resetGame();
		}
	}

	private void resetGame() {
		board = new Board();
		codeEntry.setText("");
		gameStatus.setText("");

			for(int i = 0; i < board.getGuessNum(); i++) {
				for(int j = 0; j < 3; j++) {
					feedback[i][j].setText("");
				}
			}
	}
}
