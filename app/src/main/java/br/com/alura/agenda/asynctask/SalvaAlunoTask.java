package br.com.alura.agenda.asynctask;

import br.com.alura.agenda.database.RoomAlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {
    private final RoomAlunoDAO alunoDAO;
    private final TelefoneDAO telefoneDAO;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final Aluno aluno;

    public SalvaAlunoTask(RoomAlunoDAO alunoDAO,
                          TelefoneDAO telefoneDAO,
                          Telefone telefoneFixo,
                          Telefone telefoneCelular, Aluno aluno, FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.telefoneDAO = telefoneDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.aluno = aluno;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);

        return null;
    }

}
