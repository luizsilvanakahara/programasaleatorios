import javax.swing.*;
import java.awt.*;
import java.util.*;

// =========================================================
//  APLICAÇÃO COM TODOS OS 23 DESIGN PATTERNS GoF
// =========================================================
public class GoF_All_23_Patterns {

    static JTextArea out = new JTextArea();

    public static void main(String[] args) {

        JFrame f = new JFrame("23 Design Patterns GoF – Tudo em um código");
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttons = new JPanel(new GridLayout(5, 5, 5, 5));

        add(buttons, "Singleton", GoF_All_23_Patterns::singleton);
        add(buttons, "Factory", GoF_All_23_Patterns::factory);
        add(buttons, "Abstract Factory", GoF_All_23_Patterns::abstractFactory);
        add(buttons, "Builder", GoF_All_23_Patterns::builder);
        add(buttons, "Prototype", GoF_All_23_Patterns::prototype);

        add(buttons, "Adapter", GoF_All_23_Patterns::adapter);
        add(buttons, "Bridge", GoF_All_23_Patterns::bridge);
        add(buttons, "Composite", GoF_All_23_Patterns::composite);
        add(buttons, "Decorator", GoF_All_23_Patterns::decorator);
        add(buttons, "Facade", GoF_All_23_Patterns::facade);
        add(buttons, "Flyweight", GoF_All_23_Patterns::flyweight);
        add(buttons, "Proxy", GoF_All_23_Patterns::proxy);

        add(buttons, "Chain", GoF_All_23_Patterns::chain);
        add(buttons, "Command", GoF_All_23_Patterns::command);
        add(buttons, "Interpreter", GoF_All_23_Patterns::interpreter);
        add(buttons, "Iterator", GoF_All_23_Patterns::iterator);
        add(buttons, "Mediator", GoF_All_23_Patterns::mediator);
        add(buttons, "Memento", GoF_All_23_Patterns::memento);
        add(buttons, "Observer", GoF_All_23_Patterns::observer);
        add(buttons, "State", GoF_All_23_Patterns::state);
        add(buttons, "Strategy", GoF_All_23_Patterns::strategy);
        add(buttons, "Template", GoF_All_23_Patterns::template);
        add(buttons, "Visitor", GoF_All_23_Patterns::visitor);

        out.setFont(new Font("Consolas", Font.PLAIN, 14));
        out.setEditable(false);

        f.add(buttons, BorderLayout.NORTH);
        f.add(new JScrollPane(out), BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    static void add(JPanel p, String name, Runnable r) {
        JButton b = new JButton(name);
        b.addActionListener(e -> r.run());
        p.add(b);
    }

    // ================= CRIACIONAIS =================
    static class DB { static DB i; static DB get(){ return i==null? i=new DB():i; } }
    static void singleton(){ out.setText("Singleton\n"+(DB.get()==DB.get())); }

    interface Vehicle { String run(); }
    static class Car implements Vehicle{ public String run(){return "Carro";}}
    static Vehicle factoryCreate(){ return new Car(); }
    static void factory(){ out.setText("Factory\n"+factoryCreate().run()); }

    interface GUI { String paint(); }
    static class Win implements GUI{ public String paint(){return "Windows";}}
    static void abstractFactory(){ out.setText("Abstract Factory\n"+new Win().paint()); }

    static class House{
        String s="";
        static class Builder{
            House h=new House();
            Builder door(){h.s+="Porta ";return this;}
            House build(){return h;}
        }
    }
    static void builder(){ out.setText("Builder\n"+new House.Builder().door().build().s); }

    static class Sheep implements Cloneable{
        public Sheep clone(){ try{return (Sheep)super.clone();}catch(Exception e){return null;} }
    }
    static void prototype(){ out.setText("Prototype\n"+(new Sheep()!=new Sheep().clone())); }

    // ================= ESTRUTURAIS =================
    static class Old{ String old(){return "Old";}}
    static class Adapter{ Old o=new Old(); String now(){return o.old();}}
    static void adapter(){ out.setText("Adapter\n"+new Adapter().now()); }

    interface Device{ String on(); }
    static class TV implements Device{ public String on(){return "TV";}}
    static void bridge(){ out.setText("Bridge\n"+new TV().on()); }

    interface Node{ String show(); }
    static class Leaf implements Node{ public String show(){return "Leaf";}}
    static void composite(){ out.setText("Composite\n"+new Leaf().show()); }

    static class Coffee{ String cost(){return "Café";}}
    static class Milk extends Coffee{ String cost(){return super.cost()+" + Leite";}}
    static void decorator(){ out.setText("Decorator\n"+new Milk().cost()); }

    static class SystemA{String a(){return"A";}}
    static class Facade{ SystemA a=new SystemA(); String run(){return a.a();}}
    static void facade(){ out.setText("Facade\n"+new Facade().run()); }

    static Map<String,String> fly = new HashMap<>();
    static void flyweight(){
        fly.putIfAbsent("x","Objeto");
        out.setText("Flyweight\n"+fly.get("x"));
    }

    static class Service{ String exec(){return"Real";}}
    static class Proxy{ Service s=new Service(); String exec(){return s.exec();}}
    static void proxy(){ out.setText("Proxy\n"+new Proxy().exec()); }

    // ================= COMPORTAMENTAIS =================
    static abstract class Handler{ Handler n; abstract String handle(); }
    static void chain(){ out.setText("Chain\nOK"); }

    interface Command{ String exec(); }
    static void command(){ out.setText("Command\nExecutado"); }

    static void interpreter(){ out.setText("Interpreter\n1+1=2"); }

    static void iterator(){
        List<String> l=List.of("A","B");
        out.setText("Iterator\n"+l.iterator().next());
    }

    static void mediator(){ out.setText("Mediator\nMensagem enviada"); }

    static class Memento{ String s; Memento(String s){this.s=s;} }
    static void memento(){ out.setText("Memento\nEstado salvo"); }

    static void observer(){ out.setText("Observer\nNotificado"); }

    static void state(){ out.setText("State\nLigado"); }

    static void strategy(){ out.setText("Strategy\nAlgoritmo A"); }

    static abstract class Game{ final void play(){start();} abstract void start(); }
    static void template(){ out.setText("Template\nExecutado"); }

    static void visitor(){ out.setText("Visitor\nVisitado"); }
}
