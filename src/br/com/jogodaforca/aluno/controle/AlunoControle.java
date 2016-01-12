package br.com.jogodaforca.aluno.controle;

import br.com.jogodaforca.aluno.dao.AlunoDao;
import br.com.jogodaforca.vo.AlunoVo;

public class AlunoControle {
    
    public AlunoVo cadastrarAluno(AlunoVo aluno){
        return new AlunoDao().salvar(aluno);
    }
}
