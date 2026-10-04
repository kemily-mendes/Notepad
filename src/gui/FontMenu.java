package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class FontMenu extends JDialog {
	
	private NotepadGUI source;
	private JTextField currentFontField, currentFontStyleField, currentFontSizeField;
	private JPanel currentColorBox;
	
	public FontMenu(NotepadGUI source) {
		this.source = source;
		setTitle("Font Settings");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(425, 350);
		setLocationRelativeTo(source);
		setModal(true);
		setLayout(null);
		
		addMenuComponents();
	}
	
	private void addMenuComponents() {
		addFontChooser();
		addFontStyleChooser();
		addFontSizeChooser();
		addFontColorChooser();
		
		JButton applyButton = new JButton("Apply");
		applyButton.setBackground(new Color(173, 216, 230));
		applyButton.setBounds(230, 265, 75, 25);
		applyButton.addActionListener(
			    new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	String fontType = currentFontField.getText();
			        	int fontStyle;
			        	switch (currentFontStyleField.getText()) {
			        		case "Plain":
			        			fontStyle = Font.PLAIN;
			        			break;
			        		case "Bold":
			        			fontStyle = Font.BOLD;
			        			break;
			        		case "Italic":
			        			fontStyle = Font.ITALIC;
			        			break;
			        		default:
			        			fontStyle = Font.BOLD | Font.ITALIC;
			        			break;
			        	}
			        	int fontSize = Integer.parseInt(currentFontSizeField.getText());
			        	Color fontColor = currentColorBox.getBackground();
			    		Font newFont = new Font(fontType, fontStyle, fontSize);
			    		source.getTextArea().setFont(newFont);
			    		source.getTextArea().setForeground(fontColor);
			    		FontMenu.this.dispose();
			        }
			    }
			);
		add(applyButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(255, 182, 193));
		cancelButton.setBounds(325, 265, 75, 25);
		cancelButton.addActionListener(
			    new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			    		FontMenu.this.dispose();
			        }
			    }
			);
		add(cancelButton);
	}
	
    private void addFontChooser(){
		JLabel fontLabel = new JLabel("Font: ");
		fontLabel.setBounds(10, 5, 125, 10);
		add(fontLabel);
		
		JPanel fontPanel = new JPanel();
		fontPanel.setBounds(10, 15, 125, 160);
		add(fontPanel);
		
		currentFontField = new JTextField(source.getTextArea().getFont().getFontName());
		currentFontField.setEditable(false);
		currentFontField.setPreferredSize(new Dimension(125, 25));
		fontPanel.add(currentFontField);
		
		JPanel listOfFontsPanel = new JPanel();
		listOfFontsPanel.setLayout(new BoxLayout(listOfFontsPanel, BoxLayout.Y_AXIS));
		listOfFontsPanel.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(listOfFontsPanel);
		scrollPane.setPreferredSize(new Dimension(125, 125));
		fontPanel.add(scrollPane);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();
		for(String i : fontNames) {
			JLabel fontNameLabel = new JLabel(i);
			fontNameLabel.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked (MouseEvent e) {
						currentFontField.setText(i);
					}
					@Override
					public void mouseEntered (MouseEvent e) {
	                    fontNameLabel.setOpaque(true);
	                    fontNameLabel.setBackground(Color.BLUE);
	                    fontNameLabel.setForeground(Color.WHITE);
					}
					@Override
					public void mouseExited (MouseEvent e) {
	                    fontNameLabel.setBackground(null);
	                    fontNameLabel.setForeground(null);
					}
				}
			);
			listOfFontsPanel.add(fontNameLabel);
		}
    }
	
    private void addFontStyleChooser() {
    	JLabel fontStyleLabel = new JLabel("Font Style:");
    	fontStyleLabel.setBounds(145, 5, 125, 10);
    	add(fontStyleLabel);
    	
    	JPanel fontStylePanel = new JPanel();
    	fontStylePanel.setBounds(145, 15, 125, 160);
    	add(fontStylePanel);
    	
    	int currentFontStyle = source.getTextArea().getFont().getStyle();
    	String currentFontStyleText;
    	switch(currentFontStyle) {
    		case Font.PLAIN:
    			currentFontStyleText = "Plain";
    			break;
      		case Font.BOLD:
    			currentFontStyleText = "Bold";
    			break;
      		case Font.ITALIC:
    			currentFontStyleText = "Italic";
    			break;
      		default:
    			currentFontStyleText = "Bold Italic";
    			break;
    	}
    	currentFontStyleField = new JTextField(currentFontStyleText);
    	currentFontStyleField.setPreferredSize(new Dimension(125, 25));
    	currentFontStyleField.setEditable(false);
    	fontStylePanel.add(currentFontStyleField);
    	
    	JPanel listOfFontStylesPanel = new JPanel();
    	listOfFontStylesPanel.setLayout(new BoxLayout(listOfFontStylesPanel, BoxLayout.Y_AXIS));
    	listOfFontStylesPanel.setBackground(Color.WHITE);
    	
    	JLabel plainStyleLabel = new JLabel("Plain");
    	plainStyleLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
    	listOfFontStylesPanel.add(plainStyleLabel);
    	plainStyleLabel.addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseClicked (MouseEvent e) {
					currentFontStyleField.setText(plainStyleLabel.getText());
				}
				@Override
				public void mouseEntered (MouseEvent e) {
					plainStyleLabel.setOpaque(true);
					plainStyleLabel.setBackground(Color.BLUE);
					plainStyleLabel.setForeground(Color.WHITE);
				}
				@Override
				public void mouseExited (MouseEvent e) {
					plainStyleLabel.setBackground(null);
					plainStyleLabel.setForeground(null);
				}
			}
		);
    	
    	JLabel boldStyleLabel = new JLabel("Bold");
    	boldStyleLabel.setFont(new Font("Dialog", Font.BOLD, 12));
    	listOfFontStylesPanel.add(boldStyleLabel);
    	boldStyleLabel.addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseClicked (MouseEvent e) {
					currentFontStyleField.setText(boldStyleLabel.getText());
				}
				@Override
				public void mouseEntered (MouseEvent e) {
					boldStyleLabel.setOpaque(true);
					boldStyleLabel.setBackground(Color.BLUE);
					boldStyleLabel.setForeground(Color.WHITE);
				}
				@Override
				public void mouseExited (MouseEvent e) {
					boldStyleLabel.setBackground(null);
					boldStyleLabel.setForeground(null);
				}
			}
		);
    	
    	JLabel italicStyleLabel = new JLabel("Italic");
    	italicStyleLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
    	listOfFontStylesPanel.add(italicStyleLabel);
    	italicStyleLabel.addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseClicked (MouseEvent e) {
					currentFontStyleField.setText(italicStyleLabel.getText());
				}
				@Override
				public void mouseEntered (MouseEvent e) {
					italicStyleLabel.setOpaque(true);
					italicStyleLabel.setBackground(Color.BLUE);
					italicStyleLabel.setForeground(Color.WHITE);
				}
				@Override
				public void mouseExited (MouseEvent e) {
					italicStyleLabel.setBackground(null);
					italicStyleLabel.setForeground(null);
				}
			}
		);
    	
    	JLabel boldItalicStyleLabel = new JLabel("Bold Italic");
    	boldItalicStyleLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
    	listOfFontStylesPanel.add(boldItalicStyleLabel);
    	boldItalicStyleLabel.addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseClicked (MouseEvent e) {
					currentFontStyleField.setText(boldItalicStyleLabel.getText());
				}
				@Override
				public void mouseEntered (MouseEvent e) {
					boldItalicStyleLabel.setOpaque(true);
					boldItalicStyleLabel.setBackground(Color.BLUE);
					boldItalicStyleLabel.setForeground(Color.WHITE);
				}
				@Override
				public void mouseExited (MouseEvent e) {
					boldItalicStyleLabel.setBackground(null);
					boldItalicStyleLabel.setForeground(null);
				}
			}
		);
    	
    	JScrollPane scrollPane = new JScrollPane(listOfFontStylesPanel);
    	scrollPane.setPreferredSize(new Dimension(125, 125));
    	fontStylePanel.add(scrollPane);
    }

    private void addFontSizeChooser() {
    	JLabel fontSizeLabel = new JLabel("Font Size:");
    	fontSizeLabel.setBounds(275, 5, 125, 10);
    	add(fontSizeLabel);
    	
    	JPanel fontSizePanel = new JPanel();
    	fontSizePanel.setBounds(275, 15, 125, 160);
    	add(fontSizePanel);
    	
    	currentFontSizeField = new JTextField(
    		Integer.toString(source.getTextArea().getFont().getSize())
    	);
    	currentFontSizeField.setPreferredSize(new Dimension(125, 25));
    	currentFontSizeField.setEditable(false);
    	fontSizePanel.add(currentFontSizeField);
    	
    	JPanel listOfFontSizesPanel = new JPanel();
    	listOfFontSizesPanel.setLayout(new BoxLayout(listOfFontSizesPanel, BoxLayout.Y_AXIS));
    	listOfFontSizesPanel.setBackground(Color.WHITE);
        
        for(int i = 8; i <= 72; i+=2) {
            JLabel fontSizeValueLabel = new JLabel(Integer.toString(i));
            listOfFontSizesPanel.add(fontSizeValueLabel);
            fontSizeValueLabel.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked (MouseEvent e) {
						currentFontSizeField.setText(fontSizeValueLabel.getText());
					}
					@Override
					public void mouseEntered (MouseEvent e) {
						fontSizeValueLabel.setOpaque(true);
						fontSizeValueLabel.setBackground(Color.BLUE);
						fontSizeValueLabel.setForeground(Color.WHITE);
					}
					@Override
					public void mouseExited (MouseEvent e) {
						fontSizeValueLabel.setBackground(null);
						fontSizeValueLabel.setForeground(null);
					}
				}
			);
        }

    	JScrollPane scrollPane = new JScrollPane(listOfFontSizesPanel);
        scrollPane.setPreferredSize(new Dimension(125, 125));
        fontSizePanel.add(scrollPane);
    }
    
    private void addFontColorChooser(){
    	currentColorBox = new JPanel();
    	currentColorBox.setBounds(113, 200, 23, 23);
    	currentColorBox.setBackground(source.getTextArea().getForeground());
    	currentColorBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	add(currentColorBox);
    	
    	JButton chooseColorButton = new JButton("Choose Color");
    	chooseColorButton.setBounds(10, 200, 94, 25);
    	chooseColorButton.addActionListener(
    		new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Color color = JColorChooser.showDialog(FontMenu.this, "Select a color", Color.BLACK);
    				currentColorBox.setBackground(color);
    			}
    		}
    	);
    	add(chooseColorButton);
    }
}
