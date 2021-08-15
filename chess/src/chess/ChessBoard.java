package chess;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}

public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}

	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}

	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}

	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });

	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);

	    }
	}

	public final JComponent getGui() {
	    return gui;
	}

	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}

			//================================Utilize these functions========================================//

	class Piece{
		PlayerColor color;
		PieceType type;

		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}

	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}

	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}

	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}

	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}

	public void setStatus(String inpt){
		message.setText(inpt);
	}

	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}


	private boolean checkmate;
	static int count = 0;
	static int a, b;
	Piece none = new Piece(PlayerColor.none, PieceType.none);
	Piece[][] tempboard = new Piece[8][8];

	class ButtonListener implements ActionListener{
		int x;
		int y;

		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}


		public void actionPerformed(ActionEvent e) {	// Only modify here
			// (x, y) is where the click event occured
			Piece s = getIcon(x,y);
			if(isdeadking(chessBoardStatus) || message.getText().equals("Black's turn / Checkmate") || message.getText().equals("White's turn / Checkmate"))
			{
				return;
			}
			if(message.getText().equals("Black's turn") || message.getText().equals("Black's turn / Check"))
			{
				if(s.color.equals(PlayerColor.white) && !Ispink(x,y))
				{
					Clearboard();
					return;
				}
			}
			if(message.getText().equals("White's turn") || message.getText().equals("White's turn / Check"))
			{
				if(s.color.equals(PlayerColor.black) && !Ispink(x,y))
				{
					Clearboard();
					return;
				}
			}

			if(count==0)
			{
				marking(s);
				Setintab(x,y);
				Incresingcount();
			}
			else
			{
				if(Ispink(x,y))
				{
					moving(getIcon(a,b));
					if(isdeadking(chessBoardStatus)) ending();
				}
				else Clearboard();
				Setcount();
			}

			}
			public void ending()
			{
				if(message.getText().equals("White's turn") || message.getText().equals("White's turn / Checkmate"))
				{
					message.setText("Black's turn");
				}
				else if(message.getText().equals("Black's turn") || message.getText().equals("Black's turn / Checkmate"))
				{
					message.setText("White's turn");
				}
			}
			public void marking(Piece s)
			{
				if(s.type.equals(PieceType.pawn)) {
					if (Isblack(s)) {
						if(((x+1<=7)&&(y-1>=0))&&(Iswhite(getIcon(x+1,y-1))))
						{
							markPosition(x+1,y-1);
							if(Isnone(x+1,y)) markPosition(x+1,y);
						}
						if (((x+1<=7)&&(y+1<=7))&&(Iswhite(getIcon(x+1,y+1))))
						{
							markPosition(x+1,y+1);
							if(Isnone(x+1,y)) markPosition(x+1,y);
						}

						if (x == 1) {
							for (int i = 1; i < 3; i++) {
								if (Isnone(x+i,y)) markPosition(x + i, y);
								else break;
							}
						}
						else
						{
							if (x + 1 <= 7)
							{
								if (Isnone(x+1,y)) markPosition(x + 1, y);
							}
						}


					}
					if (Iswhite(s)) {
						if((((x-1>=0)&&(y-1>=0))&&(Isblack(getIcon(x-1,y-1)))))
						{
							markPosition(x-1,y-1);
							if(Isnone(x-1,y)) markPosition(x-1,y);
						}
						if (((x-1>=0)&&(y+1<=7))&&(Isblack(getIcon(x-1,y+1))))
						{
							markPosition(x-1,y+1);
							if(Isnone(x-1,y)) markPosition(x-1,y);
						}
						if (x == 6)
						{
							for (int i = 1; i < 3; i++)
							{
								if (Isnone(x - i, y)) markPosition(x - i, y);
								else break;
							}

						}
						else
						{
							if (x - 1 >= 0)
							{
								if (Isnone(x - 1, y)) markPosition(x - 1, y);
							}
						}
						}
				}

				if(s.type.equals(PieceType.rook))
				{
					for(int i=x-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon(i,y))) markPosition(i,y);
						if(!Isnone(i,y)) break;
					}
					for(int i =x+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon(i,y))) markPosition(i,y);
						if(!Isnone(i,y)) break;
					}
					for(int i =y-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon(x,i))) markPosition(x,i);
						if(!Isnone(x,i)) break;
					}
					for(int i =y+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon(x,i))) markPosition(x,i);
						if(!Isnone(x,i)) break;
					}
				}

				if(s.type.equals(PieceType.knight))
				{
					if(x-2>=0 && y-1>=0)
					{
						if(!Issamecolor(s,getIcon(x-2,y-1))) markPosition(x-2,y-1);
					}
					if(x-1>=0 && y-2>=0)
					{
						if(!Issamecolor(s,getIcon(x-1,y-2))) markPosition(x-1,y-2);
					}

					if(x-2>=0 && y+1<=7)
					{
						if(!Issamecolor(s,getIcon(x-2,y+1))) markPosition(x-2,y+1);
					}
					if(x-1>=0 && y+2<=7)
					{
						if(!Issamecolor(s,getIcon(x-1,y+2))) markPosition(x-1,y+2);
					}
					if(x+2<=7 && y-1>=0)
					{
						if(!Issamecolor(s,getIcon(x+2,y-1))) markPosition(x+2,y-1);
					}
					if(x+1<=7 && y-2>=0)
					{
						if(!Issamecolor(s,getIcon(x+1,y-2))) markPosition(x+1,y-2);
					}

					if(x+2<=7 && y+1<=7)
					{
						if(!Issamecolor(s,getIcon(x+2,y+1))) markPosition(x+2,y+1);
					}
					if(x+1<=7 && y+2<=7)
					{
						if(!Issamecolor(s,getIcon(x+1,y+2))) markPosition(x+1,y+2);
					}
					}

				if(s.type.equals(PieceType.bishop))
				{
					for(int i = 1; (x-i>=0) && (y-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon(x-i,y-i))) markPosition(x-i,y-i);
						if(!Isnone(x-i,y-i)) break;
					}
					for(int i = 1; (x+i<=7) && (y-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon(x+i,y-i))) markPosition(x+i,y-i);
						if(!Isnone(x+i,y-i)) break;
					}
					for(int i = 1; (x-i>=0) && (y+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon(x-i,y+i))) markPosition(x-i,y+i);
						if(!Isnone(x-i,y+i)) break;
					}
					for(int i = 1; (x+i<=7) && (y+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon(x+i,y+i))) markPosition(x+i,y+i);
						if(!Isnone(x+i,y+i)) break;
					}
				}

				if(s.type.equals(PieceType.queen))
				{
					for(int i = 1; (x-i>=0) && (y-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon(x-i,y-i))) markPosition(x-i,y-i);
						if(!Isnone(x-i,y-i)) break;
					}
					for(int i = 1; (x+i<=7) && (y-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon(x+i,y-i))) markPosition(x+i,y-i);
						if(!Isnone(x+i,y-i)) break;
					}
					for(int i = 1; (x-i>=0) && (y+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon(x-i,y+i))) markPosition(x-i,y+i);
						if(!Isnone(x-i,y+i)) break;
					}
					for(int i = 1; (x+i<=7) && (y+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon(x+i,y+i))) markPosition(x+i,y+i);
						if(!Isnone(x+i,y+i)) break;
					}
					for(int i=x-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon(i,y))) markPosition(i,y);
						if(!Isnone(i,y)) break;
					}
					for(int i =x+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon(i,y))) markPosition(i,y);
						if(!Isnone(i,y)) break;
					}
					for(int i =y-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon(x,i))) markPosition(x,i);
						if(!Isnone(x,i)) break;
					}
					for(int i =y+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon(x,i))) markPosition(x,i);
						if(!Isnone(x,i)) break;
					}
				}

				if(s.type.equals(PieceType.king))
				{
					if((x-1>=0) && (y-1>=0))
					{
						if(!Issamecolor(s,getIcon(x-1,y-1))) markPosition(x-1,y-1);
					}
					if(y-1>=0)
					{
						if(!Issamecolor(s,getIcon(x,y-1))) markPosition(x,y-1);
					}
					if((x+1<=7) && (y-1>=0))
					{
						if(!Issamecolor(s,getIcon(x+1,y-1))) markPosition(x+1,y-1);
					}
					if(x-1>=0)
					{
						if(!Issamecolor(s,getIcon(x-1,y))) markPosition(x-1,y);
					}
					if(x+1<=7)
					{
						if(!Issamecolor(s,getIcon(x+1,y))) markPosition(x+1,y);
					}
					if((x-1>=0) && (y+1<=7))
					{
						if(!Issamecolor(s,getIcon(x-1,y+1))) markPosition(x-1,y+1);
					}
					if(y+1<=7)
					{
						if(!Issamecolor(s,getIcon(x,y+1))) markPosition(x,y+1);
					}
					if((x+1<=7) && (y+1<=7))
					{
						if(!Issamecolor(s,getIcon(x+1,y+1))) markPosition(x+1,y+1);
					}
				}

			}
			public void moving(Piece s)
			{
				setIcon(x,y,s); // new position
				setIcon(a,b,none); //original position
				Clearboard();
				Turnchange(s);
			}
			public boolean Isnone(int x, int y)
			{
				if(getIcon(x,y).color.equals(PlayerColor.none) || getIcon(x,y).type.equals(PieceType.none)) return true;
				return false;
			}
			public boolean Isblack(Piece s)
			{
				if(s.color.equals(PlayerColor.black)) return true;
				return false;
			}
			public boolean Iswhite(Piece s)
			{
				if (s.color.equals(PlayerColor.white)) return true;
				return false;
			}
			public boolean Ispink(int x, int y)
			{
				if(chessBoardSquares[y][x].getBackground().equals(Color.pink)) return true;
				if(chessBoardSquares[y][x].getBackground().equals(Color.pink)) return true;
				return false;
			}
			public boolean Issamecolor(Piece s1, Piece s2)
			{
				if(s1==null || s2==null) return false;
				if(s1.color.equals(s2.color)) return true;
				return false;
			}
			public boolean isdeadking(Piece[][] board)
			{
				int king_count = 0;
				for(int i=0; i<8;i++)
				{
					for(int j=0;j<8;j++)
					{
						if(board[j][i].type.equals(PieceType.king)) king_count++;
					}
				}
				if(king_count==2) return false;
				return true;
			}
			public void Setcount()
			{
				count = 0;
			}
			public void Incresingcount()
			{
				count++;
			}
			public void Setintab(int x, int y)
			{
				a = x;
				b = y;
			}
			public void Clearboard()
			{
				for(int i=0; i<8; i++)
				{
					for(int j=0; j<8; j++)
					{
						unmarkPosition(i,j);
					}
				}
			}
			public void Turnchange(Piece s)
			{
				if(Ischeck(chessBoardStatus))
					{
						if (Checkmate(Check(chessBoardStatus)))
						{
							if (s.color.equals(PlayerColor.black)) setStatus("White's turn / Checkmate");
							if (s.color.equals(PlayerColor.white)) setStatus("Black's turn / Checkmate");
						}
					else
					{
						if (s.color.equals(PlayerColor.black))
						{
							if (Check(chessBoardStatus)[1] == 1) setStatus("White's turn / Check");
							else setStatus("White's turn");
						}
						if (s.color.equals(PlayerColor.white))
						{
							if (Check(chessBoardStatus)[0] == 1) setStatus("Black's turn / Check");
							else setStatus("Black's turn");
						}
					}
				}
				else
				{
					if(s.color.equals(PlayerColor.black)) setStatus("White's turn");
					if(s.color.equals(PlayerColor.white)) setStatus("Black's turn");
				}

			}

			// function for tempboard
			public void setIcon_2(int i, int j, Piece piece)
			{
				tempboard[j][i] = piece;
			}
			public Piece getIcon_2(int i, int j)
			{
				return tempboard[j][i];
			}
			public void moving_2(int i, int j, Piece s, int u, int v)
			{
				setIcon_2(i,j,s); // new position
				setIcon_2(u,v,none);
			}
			public boolean Isnone_2(int i, int j)
			{
				if(getIcon_2(i,j).color.equals(PlayerColor.none) || getIcon_2(i,j).type.equals(PieceType.none)) return true;
				return false;
			}
			public boolean Isking_2(int i, int j)
			{
				if(getIcon_2(i,j).type.equals(PieceType.king)) return true;
				return false;
			}

			public int[] Check(Piece[][] board)
			{
				//king = {black, white}
				Piece[] king = new Piece[2];
				int[][] arr = {{0,0},{0,0}};
				int[] checked = {0,0};

				for(int i=0; i<8; i++)
				{
					for(int j=0; j<8; j++)
					{
						tempboard[j][i] = board[j][i];
						if(Isking_2(i,j))
						{
							if (Isblack(getIcon_2(i,j)))
							{
								king[0] = getIcon_2(i,j);
								arr[0][0] = i;
								arr[0][1] = j;
							}

							if (Iswhite(getIcon_2(i,j)))
							{
								king[1] = getIcon_2(i,j);
								arr[1][0] = i;
								arr[1][1] = j;
							}
						}
					}
				}

				boolean exit = false;
				int k;

				for(k=0;k<2;k++)
				{
					int x_k = arr[k][0];
					int y_k = arr[k][1];

					//직선방향 체크
					for(int i=x_k-1; i>=0; i--)
					{
						if(!Isnone_2(i,y_k))
						{
							if(Issamecolor(king[k],getIcon_2(i,y_k))) break;
							else
							{
								if(getIcon_2(i,y_k).type.equals(PieceType.rook) || getIcon_2(i,y_k).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}
					}
					for(int i =x_k+1; i<8; i++)
					{
						if(!Isnone_2(i,y_k))
						{
							if(Issamecolor(king[k],getIcon_2(i,y_k))) break;
							else
							{
								if(getIcon_2(i,y_k).type.equals(PieceType.rook) || getIcon_2(i,y_k).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}
					}
					for(int i =y_k-1; i>=0; i--)
					{
						if(!Isnone_2(x_k,i))
						{
							if(Issamecolor(king[k],getIcon_2(x_k,i))) break;
							else
							{
								if(getIcon_2(x_k,i).type.equals(PieceType.rook) || getIcon_2(x_k,i).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}
					}
					for(int i =y_k+1; i<8; i++)
					{
						if(!Isnone_2(x_k,i))
						{
							if(Issamecolor(king[k],getIcon_2(x_k,i))) break;
							else
							{
								if(getIcon_2(x_k,i).type.equals(PieceType.rook) || getIcon_2(x_k,i).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}
					}
					//대각선방향 check
					for(int i = 1; (x_k-i>=0) && (y_k-i>=0); i++)
					{
						if(!Isnone_2(x_k-i,y_k-i))
						{
							if(Issamecolor(king[k],getIcon_2(x_k-i,y_k-i))) break;
							else
							{
								if(getIcon_2(x_k-i,y_k-i).type.equals(PieceType.bishop) || getIcon_2(x_k-i,y_k-i).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}
					}
					for(int i = 1; (x_k+i<=7) && (y_k-i>=0); i++)
					{
						if(!Isnone_2(x_k+i,y_k-i))
						{
							if(Issamecolor(king[k],getIcon_2(x_k+i,y_k-i))) break;
							else
							{
								if(getIcon_2(x_k+i,y_k-i).type.equals(PieceType.bishop) || getIcon_2(x_k+i,y_k-i).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}

					}
					for(int i = 1; (x_k-i>=0) && (y_k+i<=7); i++)
					{
						if(!Isnone_2(x_k-i,y_k+i))
						{
							if(Issamecolor(king[k],getIcon_2(x_k-i,y_k+i))) break;
							else
							{
								if(getIcon_2(x_k-i,y_k+i).type.equals(PieceType.bishop) || getIcon_2(x_k-i,y_k+i).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}
					}
					for(int i = 1; (x_k+i<=7) && (y_k+i<=7); i++)
					{
						if(!Isnone_2(x_k+i,y_k+i))
						{
							if(Issamecolor(king[k],getIcon_2(x_k+i,y_k+i))) break;
							else
							{
								if(getIcon_2(x_k+i,y_k+i).type.equals(PieceType.bishop) || getIcon_2(x_k+i,y_k+i).type.equals(PieceType.queen)) {exit=true; break;}
								else break;
							}
						}
					}
					//knight check
					if(x_k-2>=0 && y_k-1>=0)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k-2,y_k-1)) && getIcon_2(x_k-2,y_k-1).type.equals(PieceType.knight)) {exit=true;}
					}
					if(x_k-1>=0 && y_k-2>=0)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k-1,y_k-2)) && getIcon_2(x_k-1,y_k-2).type.equals(PieceType.knight)) {exit=true;}
					}
					if(x_k-2>=0 && y_k+1<=7)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k-2,y_k+1)) && getIcon_2(x_k-2,y_k+1).type.equals(PieceType.knight)) {exit=true;}
					}
					if(x_k-1>=0 && y_k+2<=7)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k-1,y_k+2)) && getIcon_2(x_k-1,y_k+2).type.equals(PieceType.knight)) {exit=true;}
					}
					if(x_k+2<=7 && y_k-1>=0)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k+2,y_k-1)) && getIcon_2(x_k+2,y_k-1).type.equals(PieceType.knight)) {exit=true;}
					}
					if(x_k+1<=7 && y_k-2>=0)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k+1,y_k-2)) && getIcon_2(x_k+1,y_k-2).type.equals(PieceType.knight)) {exit=true;}
					}
					if(x_k+2<=7 && y_k+1<=7)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k+2,y_k+1)) && getIcon_2(x_k+2,y_k+1).type.equals(PieceType.knight)) {exit=true;}
					}
					if(x_k+1<=7 && y_k+2<=7)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k+1,y_k+2)) && getIcon_2(x_k+1,y_k+2).type.equals(PieceType.knight)) {exit=true;}
					}
					//pawn check
					if(k==0)
					{
						if(x_k+1<=7 && y_k-1>=0)
						{
							if(!Issamecolor(king[k],getIcon_2(x_k+1,y_k-1)) && getIcon_2(x_k+1,y_k-1).type.equals(PieceType.pawn)) {exit=true;}
						}
						if(x_k+1<=7 && y_k+1<=7)
						{
							if(!Issamecolor(king[k],getIcon_2(x_k+1,y_k+1)) && getIcon_2(x_k+1,y_k+1).type.equals(PieceType.pawn)) {exit=true;}
						}
					}
					else
					{
						if(x_k-1>=0 && y_k-1>=0)
						{
							if(!Issamecolor(king[k],getIcon_2(x_k-1,y_k-1)) && getIcon_2(x_k-1,y_k-1).type.equals(PieceType.pawn)) {exit=true;}
						}
						if(x_k-1>=0 && y_k+1<=7)
						{
							if(!Issamecolor(king[k],getIcon_2(x_k-1,y_k+1)) && getIcon_2(x_k-1,y_k+1).type.equals(PieceType.pawn)) {exit=true;}
						}
					}
					// king check
					if((x_k-1>=0) && (y_k-1>=0))
					{
						if(!Issamecolor(king[k],getIcon_2(x_k-1,y_k-1)) && getIcon_2(x_k-1,y_k-1).type.equals(PieceType.king)) {exit=true;}
					}
					if(y_k-1>=0)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k,y_k-1)) && getIcon_2(x_k,y_k-1).type.equals(PieceType.king)) {exit=true;}
					}
					if((x_k+1<=7) && (y_k-1>=0))
					{
						if(!Issamecolor(king[k],getIcon_2(x_k+1,y_k-1)) && getIcon_2(x_k+1,y_k-1).type.equals(PieceType.king)) {exit=true;}
					}
					if(x_k-1>=0)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k-1,y_k)) && getIcon_2(x_k-1,y_k).type.equals(PieceType.king)) {exit=true;}
					}
					if(x_k+1<=7)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k+1,y_k)) && getIcon_2(x_k+1,y_k).type.equals(PieceType.king)) {exit=true;}
					}
					if((x_k-1>=0) && (y_k+1<=7))
					{
						if(!Issamecolor(king[k],getIcon_2(x_k-1,y_k+1)) && getIcon_2(x_k-1,y_k+1).type.equals(PieceType.king)) {exit=true;}
					}
					if(y_k+1<=7)
					{
						if(!Issamecolor(king[k],getIcon_2(x_k,y_k+1)) && getIcon_2(x_k,y_k+1).type.equals(PieceType.king)) {exit=true;}
					}
					if((x_k+1<=7) && (y_k+1<=7))
					{
						if(!Issamecolor(king[k],getIcon_2(x_k+1,y_k+1)) && getIcon_2(x_k+1,y_k+1).type.equals(PieceType.king)) {exit=true;};
					}

					if(exit) {checked[k] = 1; exit = false;}
				}
				return checked;
			}

			public boolean Ischeck(Piece[][] board)
			{
				if(Check(board)[0]==0 && Check(board)[1]==0) return false;
				return true;
			}
			// move for attack
			public void Movable(Piece s, int m, int n)
			{
				// (m,n): s의 좌표
				if(s.type.equals(PieceType.pawn)) {
					if (Isblack(s)) {
						if(((m+1<=7)&&(n-1>=0))&&(Iswhite(getIcon_2(m+1,n-1))))
						{
							moving_2(m+1,n-1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+1,n-1);
						}
						if (((m+1<=7)&&(n+1<=7))&&(Iswhite(getIcon_2(m+1,n+1))))
						{
							moving_2(m+1,n+1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+1,n+1);
						}
					}
					if (Iswhite(s)) {
						if((((m-1>=0)&&(n-1>=0))&&(Isblack(getIcon_2(m-1,n-1)))))
						{
							moving_2(m-1,n-1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-1,n-1);
						}
						if (((m-1>=0)&&(n+1<=7))&&(Isblack(getIcon_2(m-1,n+1))))
						{
							moving_2(m-1,n+1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-1,n+1);
						}
					}
				}

				if(s.type.equals(PieceType.rook))
				{
					for(int i=m-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon_2(i,n)))
						{
							moving_2(i,n,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,i,n);
						}
						if(!Isnone_2(i,n)) break;
					}
					for(int i =m+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon_2(i,n)))
						{
							moving_2(i,n,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,i,n);
						}
						if(!Isnone_2(i,n)) break;
					}
					for(int i =n-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon_2(m,i)))
						{
							moving_2(m,i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m,i);
						}
						if(!Isnone_2(m,i)) break;
					}
					for(int i =n+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon_2(m,i)))
						{
							moving_2(m,i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m,i);
						}
						if(!Isnone_2(m,i)) break;
					}
				}

				if(s.type.equals(PieceType.knight))
				{
					if(m-2>=0 && n-1>=0)
					{
						if(!Issamecolor(s,getIcon_2(m-2,n-1)))
						{
							moving_2(m-2,n-1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-2,n-1);
						}
					}
					if(m-1>=0 && n-2>=0)
					{
						if(!Issamecolor(s,getIcon_2(m-1,n-2)))
						{
							moving_2(m-1,n-2,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-1,n-2);
						}
					}

					if(m-2>=0 && n+1<=7)
					{
						if(!Issamecolor(s,getIcon_2(m-2,n+1)))
						{
							moving_2(m-2,n+1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-2,n+1);
						}
					}
					if(m-1>=0 && n+2<=7)
					{
						if(!Issamecolor(s,getIcon_2(m-1,n+2)))
						{
							moving_2(m-1,n+2,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-1,n+2);
						}
					}
					if(m+2<=7 && n-1>=0)
					{
						if(!Issamecolor(s,getIcon_2(m+2,n-1)))
						{
							moving_2(m+2,n-1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+2,n-1);
						}

					}
					if(m+1<=7 && n-2>=0)
					{
						if(!Issamecolor(s,getIcon_2(m+1,n-2)))
						{
							moving_2(m+1,n-2,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+1,n-2);
						}
					}
					if(m+2<=7 && n+1<=7)
					{
						if(!Issamecolor(s,getIcon_2(m+2,n+1)))
						{
							moving_2(m+2,n+1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+2,n+1);
						}
					}
					if(m+1<=7 && n+2<=7)
					{
						if(!Issamecolor(s,getIcon_2(m+1,n+2)))
						{
							moving_2(m+1,n+2,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+1,n+2);
						}
					}
				}

				if(s.type.equals(PieceType.bishop))
				{
					for(int i = 1; (m-i>=0) && (n-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon_2(m-i,n-i)))
						{
							moving_2(m-i,n-i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-i,n-i);
						}
						if(!Isnone_2(m-i,n-i)) break;

					}
					for(int i = 1; (m+i<=7) && (n-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon_2(m+i,n-i)))
						{
							moving_2(m+i,n-i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+i,n-i);
						}
						if(!Isnone_2(m+i,n-i)) break;
					}
					for(int i = 1; (m-i>=0) && (n+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon_2(m-i,n+i)))
						{
							moving_2(m-i,n+i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-i,n+i);
						}
						if(!Isnone_2(m-i,n+i)) break;
					}
					for(int i = 1; (m+i<=7) && (n+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon_2(m+i,n+i)))
						{
							moving_2(m+i,n+i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+i,n+i);
						}
						if(!Isnone_2(m+i,n+i)) break;
					}
				}

				if(s.type.equals(PieceType.queen))
				{
					for(int i = 1; (m-i>=0) && (n-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon_2(m-i,n-i)))
						{
							moving_2(m-i,n-i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-i,n-i);
						}
						if(!Isnone_2(m-i,n-i)) break;

					}
					for(int i = 1; (m+i<=7) && (n-i>=0); i++)
					{
						if(!Issamecolor(s,getIcon_2(m+i,n-i)))
						{
							moving_2(m+i,n-i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+i,n-i);
						}
						if(!Isnone_2(m+i,n-i)) break;
					}
					for(int i = 1; (m-i>=0) && (n+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon_2(m-i,n+i)))
						{
							moving_2(m-i,n+i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-i,n+i);
						}
						if(!Isnone_2(m-i,n+i)) break;
					}
					for(int i = 1; (m+i<=7) && (n+i<=7); i++)
					{
						if(!Issamecolor(s,getIcon_2(m+i,n+i)))
						{
							moving_2(m+i,n+i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+i,n+i);
						}
						if(!Isnone_2(m+i,n+i)) break;
					}

					for(int i=m-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon_2(i,n)))
						{
							moving_2(i,n,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,i,n);
						}
						if(!Isnone_2(i,n)) break;
					}
					for(int i =m+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon_2(i,n)))
						{
							moving_2(i,n,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,i,n);
						}
						if(!Isnone_2(i,n)) break;
					}
					for(int i =n-1; i>=0; i--)
					{
						if(!Issamecolor(s,getIcon_2(m,i)))
						{
							moving_2(m,i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m,i);
						}
						if(!Isnone_2(m,i)) break;
					}
					for(int i =n+1; i<8; i++)
					{
						if(!Issamecolor(s,getIcon_2(m,i)))
						{
							moving_2(m,i,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m,i);
						}
						if(!Isnone_2(m,i)) break;
					}
				}

				if(s.type.equals(PieceType.king))
				{
					if((m-1>=0) && (n-1>=0))
					{
						if(!Issamecolor(s,getIcon_2(m-1,n-1)))
						{
							moving_2(m-1,n-1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-1,n-1);
						}
					}
					if(n-1>=0)
					{
						if(!Issamecolor(s,getIcon_2(m,n-1)))
						{
							moving_2(m,n-1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m,n-1);
						}
					}
					if((m+1<=7) && (n-1>=0))
					{
						if(!Issamecolor(s,getIcon_2(m+1,n-1)))
						{
							moving_2(m+1,n-1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+1,n-1);
						}
					}
					if(m-1>=0)
					{
						if(!Issamecolor(s,getIcon_2(m-1,n)))
						{
							moving_2(m-1,n,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-1,n);
						}
					}
					if(m+1<=7)
					{
						if(!Issamecolor(s,getIcon_2(m+1,n)))
						{
							moving_2(m+1,n,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+1,n);
						}
					}
					if((m-1>=0) && (n+1<=7))
					{
						if(!Issamecolor(s,getIcon_2(m-1,n+1)))
						{
							moving_2(m-1,n+1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m-1,n+1);
						}
					}
					if(n+1<=7)
					{
						if(!Issamecolor(s,getIcon_2(m,n+1)))
						{
							moving_2(m,n+1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m,n+1);
						}
					}
					if((m+1<=7) && (n+1<=7))
					{
						if(!Issamecolor(s,getIcon_2(m+1,n+1)))
						{
							moving_2(m+1,n+1,s,m,n);
							if(!Ischeck(tempboard)) {checkmate = false;}
							moving_2(m,n,s,m+1,n+1);
						}
					}
				}
			}

			public boolean Checkmate(int[] k)
			{
				checkmate = true;
				Piece[][] team = new Piece[8][8];

				if(k[0]==1) // black king is in check
				{
					for(int i=0; i<8; i++)
					{
						for(int j=0; j<8; j++)
						{
							if(Isblack(getIcon_2(i,j)))
							{
								team[j][i] = getIcon_2(i,j);
							}
						}
					}
				}

				if(k[1]==1) // white king is in check
				{
					for(int i=0; i<8; i++)
					{
						for(int j=0; j<8; j++)
						{
							if(Iswhite(getIcon_2(i,j)))
							{
								team[j][i] = getIcon_2(i,j);
							}
						}
					}
				}

				// verify checkmate
				for(int i=0; i<8; i++)
				{
					for(int j=0; j<8; j++)
					{
						if(team[j][i]==null) continue;
						Movable(team[j][i],i,j);
					}
				}

				if(checkmate) return true;
				return false;
			}

		}

	void onInitiateBoard(){
		setStatus("Black's turn");
	}
}