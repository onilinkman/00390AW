
package pkg00390aw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class ListaDeMusicas implements Serializable{
    private Queue<File> listaTemas=new LinkedList();
    private Queue<File> temasPorDefecto=new LinkedList();
    private Queue<File> listaPedidos=new LinkedList();
    private Queue<String> lugar=new LinkedList();
    private Queue<File> sala=new LinkedList();
    private Queue<File> mesa=new LinkedList();
    private Queue<File> barra=new LinkedList();
    public ListaDeMusicas(){
        
    }
    public Queue<File> buscarTema(String txt){
        Queue<File> aux=new LinkedList();
        aux.addAll(this.listaTemas);
        Queue<File> tem=new LinkedList();
        while(!aux.isEmpty()){
            File f=aux.remove();
            if(esParte(f.getName(),txt)){
                tem.add(f);
            }
        }
        return tem;
    }
    
    public boolean esParte(String cancion,String nom){//Busca PARTES del texto que existan
        for(int i=0;i<=cancion.length()-nom.length();i++){
            if(cancion.substring(i, i+nom.length()).equalsIgnoreCase(nom)){
                return true;
            }
        }
        return false;
    }
    
    public void eliminarPorDefecto(int[] x){
        for(int i=0;i<x.length;i++){
            Queue<File> aux=new LinkedList();
            int j=0;
            while(!temasPorDefecto.isEmpty()){
                File f=temasPorDefecto.remove();
                if(j!=x[i]){
                    aux.add(f);
                }
                j++;
            }
            temasPorDefecto.addAll(aux);
        }
    }
    public void eliminarTemas(int[] x){
        for(int i=0;i<x.length;i++){
            Queue<File> aux=new LinkedList();
            int j=0;
            while(!listaTemas.isEmpty()){
                File f=listaTemas.remove();
                if(j!=x[i]){
                    aux.add(f);
                }
                j++;
            }
            listaTemas.addAll(aux);
        }
    }
    
    public void pasarAPedidos(int[] x,String lugar){
        for(int i=0;i<x.length;i++){
            Queue<File> aux=new LinkedList();
            aux.addAll(listaTemas);
            int j=0;
            while(!aux.isEmpty()){
                File f=aux.remove();
                if(j==x[i]){
                    listaPedidos.add(f);
                    this.lugar.add(lugar);
                }
                j++;
            }
        }
    }
    
    
    public void guardar(ListaDeMusicas l){
        try{
            ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream(".\\musicasListas.dat"));
            o.writeObject(l);
            o.close();
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
    public ListaDeMusicas recuperar() throws IOException, ClassNotFoundException{
        ObjectInputStream o=new ObjectInputStream(new FileInputStream(".\\musicasListas.dat"));
        ListaDeMusicas l=(ListaDeMusicas) o.readObject();
        o.close();
        return l;
    }

    public Queue<File> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(Queue<File> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public Queue<File> getTemasPorDefecto() {
        return temasPorDefecto;
    }

    public void setTemasPorDefecto(Queue<File> temasPorDefecto) {
        this.temasPorDefecto = temasPorDefecto;
    }

    public Queue<File> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(Queue<File> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public Queue<String> getLugar() {
        return lugar;
    }

    public void setLugar(Queue<String> lugar) {
        this.lugar = lugar;
    }

    public Queue<File> getSala() {
        return sala;
    }

    public void setSala(Queue<File> sala) {
        this.sala = sala;
    }

    public Queue<File> getMesa() {
        return mesa;
    }

    public void setMesa(Queue<File> mesa) {
        this.mesa = mesa;
    }

    public Queue<File> getBarra() {
        return barra;
    }

    public void setBarra(Queue<File> barra) {
        this.barra = barra;
    }
    
}
