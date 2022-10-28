package sp1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class GUI {
	
	static void createAndDisplay() {
		JFrame frame = new JFrame("First example");
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel groupContainer = new JPanel();
		groupContainer.setLayout(new GridLayout(1, 9));
		LocalDate today = LocalDate.now();
		addWeek(groupContainer, today);
		frame.add(groupContainer);
		frame.setVisible(true);
	}
	
	private static void addWeek(JPanel groupContainer, LocalDate today) {
		addWeekButton(groupContainer, "<");
		for(int i = 0; i < 7; i++) {
			addWeekDay(groupContainer, today, i);
		}
		addWeekButton(groupContainer, ">");
	}
	private static void addWeekDay(JPanel groupContainer, LocalDate today, int i) {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		LocalDate day = today;
		day = day.minusDays(today.getDayOfWeek().getValue() - 1 - i);
		
		container.setBorder(BorderFactory.createRaisedBevelBorder());
		JLabel dayOfWeekLabel = new JLabel(day.getDayOfWeek().toString());
		JLabel dateLabel = new JLabel(day.getDayOfMonth() + "/" + day.getMonthValue());
		JPanel labelContainer = new JPanel();
		labelContainer.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		labelContainer.add(dayOfWeekLabel, c);
		c.gridy = 1;
		labelContainer.add(dateLabel, c);
		container.add(labelContainer, BorderLayout.NORTH);
		JTextField textField = new JTextField("Add note");
		
		textField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textField.setText("");
            }
        });
		
		JButton button = new JButton("Save");
		JPanel subContainer = new JPanel();
		subContainer.setLayout(new GridLayout(2, 1));
		subContainer.setBorder(new EmptyBorder(1, 1, 1, 1));
		subContainer.add(textField);
		subContainer.add(button);
		JPanel noteContainer = new JPanel();
		noteContainer.setLayout(new BoxLayout(noteContainer, BoxLayout.Y_AXIS));
		addButtonListener(button, noteContainer, textField);
		container.add(noteContainer);
		container.add(subContainer, BorderLayout.SOUTH);
		if(day.equals(today) && today.equals(LocalDate.now())) {
			container.setBackground(Color.cyan);
			subContainer.setBackground(Color.cyan);
			labelContainer.setBackground(Color.cyan);
			noteContainer.setBackground(Color.cyan);
		}else {
			container.setBackground(Color.white);
			subContainer.setBackground(Color.white);
			labelContainer.setBackground(Color.white);
			noteContainer.setBackground(Color.white);
		}
		groupContainer.add(container);
	}

	private static void addWeekButton(JPanel groupContainer, String text) {
		JPanel container = new JPanel();
		container.add(new JButton(text));
		groupContainer.add(container);
	}

	private static void addButtonListener(JButton b, JPanel p, JTextField tf) {
		ActionListener bListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextArea textArea = new JTextArea(tf.getText());
				textArea.setLineWrap(true);
				p.add(textArea);
				p.revalidate();
				p.repaint();
				tf.setText("");
			}
		};
		b.addActionListener(bListener);
	}
}
