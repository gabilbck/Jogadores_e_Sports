package dominio_laiz_e_gabrieli;

import java.util.List;

public interface EquipeInterface{
	void addEquipe(Equipe equipe) throws Exception;		//adicionar
    Equipe searchEquipe(int id) throws Exception;     	//procurar
    void uptEquipe(Equipe equipe) throws Exception;		//atualizar
    void delEquipe(int id) throws Exception;        	//deletar
    List<Equipe> listEquipes() throws Exception;      //listar
}