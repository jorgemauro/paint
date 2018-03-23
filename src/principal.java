import tela.Painel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class principal {
    JButton clearBtn,blackBtn, blueBtn,redBtn,magentaBtn, greenBtn, recBtn;
    Painel painel;
    ActionListener actionListener= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==clearBtn){
                painel.clear();
            }else if (e.getSource()==blackBtn){
                painel.black();
            }else if (e.getSource()==blueBtn){
                painel.blue();
            }else if (e.getSource()==redBtn){
                painel.red();
            }else if (e.getSource()==magentaBtn){
                painel.magenta();
            }else if (e.getSource()==greenBtn){
                painel.green();
            }else if(e.getSource()==recBtn){
                painel.setFerramenta("reta");
            }
        }
    };
    public static void main(String[] args) {
        new principal().show();
    }
    public void show(){
        JFrame frame=new JFrame("Paint");
        Container content=frame.getContentPane();
        content.setLayout(new BorderLayout());
        painel = new Painel();
        content.add(painel,BorderLayout.CENTER);
        JPanel controls = new JPanel();
        JPanel ferramentas = new JPanel();
        clearBtn= new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        blackBtn= new JButton("Preto");
        blackBtn.addActionListener(actionListener);
        greenBtn= new JButton("Verde");
        greenBtn.addActionListener(actionListener);
        magentaBtn= new JButton("Magenta");
        magentaBtn.addActionListener(actionListener);
        blueBtn= new JButton("Azul");
        blueBtn.addActionListener(actionListener);
        redBtn= new JButton("Vermelho");
        redBtn.addActionListener(actionListener);
        recBtn = new JButton("reta");
        recBtn.addActionListener(actionListener);
        controls.add(clearBtn);
        controls.add(blackBtn);
        controls.add(blueBtn);
        controls.add(greenBtn);
        controls.add(magentaBtn);
        controls.add(redBtn);
        ferramentas.add(recBtn);
        content.add(controls,BorderLayout.NORTH);
        content.add(ferramentas,BorderLayout.EAST);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
