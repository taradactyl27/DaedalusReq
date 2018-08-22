import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class MazeDisplay extends JComponent implements MouseListener,
						       MouseMotionListener, ActionListener, ChangeListener
{
    public Image image;
    private int speed;
    public int cellSize;
    public JFrame frame;
    private String operation;
    public int numRows;
    public int numCols;
    private int[] mouseLoc;
    private JButton[] buttons;
    private JMenuBar menuBar;
    private JSlider slider;
    private JLabel sliderLabel;
    private JMenu menu, submenu;
    private JMenuItem menuItem;
    public JCheckBox check;
    private JTextField text;
    
   
    public MazeDisplay(String title, int numRows, int numCols, String[] buttonNames, String[] fileNames)
    {
	this.numRows = numRows;
	this.numCols = numCols;
	operation = "Nothing";
	mouseLoc = null;
        setSize(1, 1);
	speed = 10;
        setVisible(true);
	menuBar = new JMenuBar();
	menu = new JMenu("Load");
	menu.getAccessibleContext().setAccessibleDescription("Load Your Files");
	menu.setMnemonic(KeyEvent.VK_A);
	menuBar.add(menu);
	for(String x : fileNames){
	    menuItem = new JMenuItem(x,KeyEvent.VK_T);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext().setAccessibleDescription("A file");
	    menuItem.setActionCommand(x);
	    menuItem.addActionListener(this);
	    menu.add(menuItem);
	}
	menuBar.add(menu);
	check = new JCheckBox();
      	
	//determine cell size
	cellSize = Math.max(1, 600 / Math.max(numRows, numCols));
	image = new BufferedImage(numCols * cellSize, numRows * cellSize, BufferedImage.TYPE_INT_RGB);
    
	frame = new JFrame(title);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
	
	JPanel topPanel = new JPanel();
	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
	frame.getContentPane().add(topPanel);
	
	setPreferredSize(new Dimension(numCols * cellSize, numRows * cellSize));
	addMouseListener(this);
	addMouseMotionListener(this);
	topPanel.add(this);

	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
	topPanel.add(buttonPanel);
    
	buttons = new JButton[buttonNames.length];
	JLabel label = new JLabel("Progressive Solve");
	buttonPanel.add(check);
	buttonPanel.add(label);
	text = new JTextField("HEIGHT,WIDTH",1);
	buttonPanel.add(text);
	for (int i = 0; i < buttons.length; i++)
	    {
		buttons[i] = new JButton(buttonNames[i]);
		buttons[i].setActionCommand(buttonNames[i]);
		buttons[i].addActionListener(this);
		buttonPanel.add(buttons[i]);
	    }
	sliderLabel = new JLabel("Solving Speed", JLabel.CENTER);
	sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
	slider.addChangeListener(this);
	slider.setMajorTickSpacing(1);
	slider.setPaintTicks(true);
	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
	labelTable.put(new Integer(0), new JLabel("Fast"));
	labelTable.put(new Integer(100), new JLabel("Slow"));
	slider.setLabelTable(labelTable);
	slider.setPaintLabels(true);
	
	frame.getContentPane().add(slider);
	frame.getContentPane().add(sliderLabel);
	buttons[1].setSelected(true);
	frame.pack();
	frame.setJMenuBar(menuBar);
	frame.setVisible(true);
	
    }
    public void updateDisplay(Maze maze){
	 for(int r = 0; r < maze.getRows(); r++){
	    for(int c = 0; c < maze.getCols(); c++){
		int[] rgb = maze.getSquareColor(r, c);
		setColor(r, c, new Color(rgb[0], rgb[1], rgb[2]));
	    }	
	}
     }
    public void paintComponent(Graphics g)
    {
	g.drawImage(image, 0, 0, null);
    }
  
    public void pause(int milliseconds)
    {
	try
	    {
		Thread.sleep(milliseconds);
	    }
	catch(InterruptedException e)
	    {
		throw new RuntimeException(e);
	    }
    }
  
    public int[] getMouseLocation()
    {
	return mouseLoc;
    }
  
    public String getOperation()
    {
	return operation;
    }
     public void setOperation(String tool)
    {
	operation = tool;
    }
  
    public void setColor(int row, int col, Color color)
    {
	Graphics g = image.getGraphics();
	g.setColor(color);
	g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
    }
  
    public void mouseClicked(MouseEvent e)
    {
    }
  
    public void mousePressed(MouseEvent e)
    {
	mouseLoc = toLocation(e);
    }
  
    public void mouseReleased(MouseEvent e)
    {
	mouseLoc = null;
    }
  
    public void mouseEntered(MouseEvent e)
    {
    }
  
    public void mouseExited(MouseEvent e)
    {
    }
  
    public void mouseMoved(MouseEvent e)
    {
    }
  
    public void mouseDragged(MouseEvent e)
    {
	mouseLoc = toLocation(e);
    }
    public int getSpeed(){
	return speed;
           }
    private int[] toLocation(MouseEvent e)
    {
	int row = e.getY() / cellSize;
	int col = e.getX() / cellSize;
	if (row < 0 || row >= numRows || col < 0 || col >= numCols)
	    return null;
	int[] loc = new int[2];
	loc[0] = row;
	loc[1] = col;
	return loc;
    }
    public String getDims(){
	return text.getText();
    }
    public void stateChanged(ChangeEvent e)
    {
	speed = slider.getValue();
    }  
    public void actionPerformed(ActionEvent e)
    {
	operation = e.getActionCommand();
	for (JButton button : buttons)
	    button.setSelected(false);
    }
  
  
}
