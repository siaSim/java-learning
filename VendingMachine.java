import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachine extends JFrame {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VendingMachine();
            }
        });
	}
	
    private JButton milkCoffeeButton;
    private JButton sugarCoffeeButton;
    private JButton blackCoffeeButton;
    private JButton insertButton;
    private JButton returnButton;
    private JTextField cashInputField;
    private JTextField balanceField;
    private JLabel messageLabel;
    private JLabel balanceLabel;
    private JLabel cashLabel;
    private int balance;
    private int milkCoffeeCount;
    private int sugarCoffeeCount;
    private int blackCoffeeCount;
    
    public VendingMachine() {
        setTitle("자판기");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // 잔액 텍스트 레이블 생성
        balanceLabel = new JLabel("잔액: ");
        
        // 커피 버튼들 생성
        milkCoffeeButton = new JButton("밀크커피(300원)");
        sugarCoffeeButton = new JButton("설탕커피(200원)");
        blackCoffeeButton = new JButton("블랙커피(100원)");
        
        // 현금 투입 레이블 생성
        cashLabel = new JLabel("현금 투입: ");
        
        // 투입 버튼 생성
        insertButton = new JButton("투입");
        
        // 반환 버튼 생성
        returnButton = new JButton("반환");
        
        // 현금 입력 필드 생성
        cashInputField = new JTextField(10);
        
        // 잔액 표시 필드 생성 및 편집 불가능 설정
        balanceField = new JTextField(10);
        balanceField.setEditable(false);
        
        // 잔액 표시 필드 생성 및 편집 불가능 설정
        messageLabel = new JLabel("음료를 선택하세요.");

        // 컴포넌트들을 프레임에 추가
        add(balanceLabel);
        add(messageLabel);
        add(balanceField);
        add(milkCoffeeButton);
        add(sugarCoffeeButton);
        add(blackCoffeeButton);
        add(cashLabel);
        add(cashInputField);
        add(insertButton);
        add(returnButton);
        add(messageLabel);

        milkCoffeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCoffeeSelection(300, "밀크");
                
            }
        });

        sugarCoffeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCoffeeSelection(200, "설탕");
              
            }
        });

        blackCoffeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCoffeeSelection(100, "블랙");
                
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertCash();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBalance();
            }
        });

        setVisible(true);
        
    }

    private void handleCoffeeSelection(int coffeePrice, String coffeeType) {
        if (balance >= coffeePrice) {
            balance -= coffeePrice;
            balanceField.setText(Integer.toString(balance));
            
            if (balance >= coffeePrice) {
                if (coffeeType.equals("밀크") && milkCoffeeCount >= 3) {
                    String message = "재료(밀크)가 부족합니다. 죄송하지만 다음에 이용 부탁 드립니다.";
                    messageLabel.setText(message);
                    return;
                }

                if (coffeeType.equals("설탕") && sugarCoffeeCount >= 3) {
                    String message = "재료(설탕)가 부족합니다. 죄송하지만 다음에 이용 부탁 드립니다.";
                    messageLabel.setText(message);
                    return;
                }

                if (coffeeType.equals("블랙") && blackCoffeeCount >= 3) {
                    String message = "재료(블랙)가 부족합니다. 죄송하지만 다음에 이용 부탁 드립니다.";
                    messageLabel.setText(message);
                    return;
                }

                balance -= coffeePrice;
            if (coffeePrice == 300) {
                coffeeType = "밀크";
            } else if (coffeePrice == 200) {
                coffeeType = "설탕";
            } else {
                coffeeType = "블랙";
            }
            messageLabel.setText(coffeeType + " 커피가 나옵니다. 맛있게 드시기 바랍니다.");

            if (balance >= 300) {
                milkCoffeeButton.setEnabled(true);
                milkCoffeeButton.setBackground(Color.YELLOW);
            } else {
                milkCoffeeButton.setEnabled(false);
                milkCoffeeButton.setBackground(Color.GRAY);
            }

            if (balance >= 200) {
                sugarCoffeeButton.setEnabled(true);
                sugarCoffeeButton.setBackground(Color.YELLOW);
            } else {
                sugarCoffeeButton.setEnabled(false);
                sugarCoffeeButton.setBackground(Color.GRAY);
            }

            if (balance >= 100) {
                blackCoffeeButton.setEnabled(true);
                blackCoffeeButton.setBackground(Color.YELLOW);
            } else {
                blackCoffeeButton.setEnabled(false);
                blackCoffeeButton.setBackground(Color.GRAY);
            }
        } else {
            messageLabel.setText("잔액이 부족합니다. 죄송하지만 다음에 이용 부탁 드립니다.");
        }
        }
    }
    private void returnBalance() {
        int returnedBalance = balance;
        balance = 0;
        balanceField.setText("0원");
        cashInputField.setText("0");

        String message = "잔액 " + returnedBalance + "원이 반환되었습니다.";
        messageLabel.setText(message);
    }


   private void insertCash() {
    int cash = Integer.parseInt(cashInputField.getText());
    if (cash == 100 || cash == 500 || cash == 1000) {
        balance += cash;
        balanceField.setText(Integer.toString(balance) + "원");
        cashInputField.setText("0");

        if (balance >= 300) {
            milkCoffeeButton.setEnabled(true);
            milkCoffeeButton.setBackground(Color.YELLOW);
        } else {
            milkCoffeeButton.setEnabled(false);
            milkCoffeeButton.setBackground(Color.GRAY);
        }

        if (balance >= 200) {
            sugarCoffeeButton.setEnabled(true);
            sugarCoffeeButton.setBackground(Color.YELLOW);
        } else {
            sugarCoffeeButton.setEnabled(false);
            sugarCoffeeButton.setBackground(Color.GRAY);
        }

        if (balance >= 100) {
            blackCoffeeButton.setEnabled(true);
            blackCoffeeButton.setBackground(Color.YELLOW);
        } else {
            blackCoffeeButton.setEnabled(false);
            blackCoffeeButton.setBackground(Color.GRAY);
        }
    } else {
        messageLabel.setText("100원, 500원, 1000원만 입력 가능합니다.");
    }
}

}