package jaime.galiana.ejemplobindingactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import jaime.galiana.ejemplobindingactivity.Modelos.Alumno;
import jaime.galiana.ejemplobindingactivity.databinding.ActivityAddAlumndoBinding;

public class AddAlumndoActivity extends AppCompatActivity {
    private ActivityAddAlumndoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_alumndo);

        binding = ActivityAddAlumndoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnAceptarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //añadir lo que escriben al alumno
                Alumno alumno = crearAlumno();
                if (alumno == null){
                    Toast.makeText(AddAlumndoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }else{
                    //enviar la informacion al principal
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO", alumno);
                    intent.putExtras(bundle);

                    setResult(RESULT_OK, intent);

                    //terminar
                    finish();
                }
            }
        });
    }

    private Alumno crearAlumno() {
        Alumno alumno;

        if (binding.txtNombreAddAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtApellidosAddAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.spCiclosAddAlumno.getSelectedItemPosition() == 0){
            return null;
        }
        if (!binding.rbGrupoAAddAlumno.isChecked() && !binding.rbGrupoBAddAlumno.isChecked() && !binding.rbGrupoCAddAlumno.isChecked()){
            return null;
        }
        RadioButton rb = findViewById(binding.rgGrupoAddAlumno.getCheckedRadioButtonId());
        char letra = rb.getText().charAt(rb.getText().length()-1);


        alumno = new Alumno(binding.txtNombreAddAlumno.getText().toString(), binding.txtApellidosAddAlumno.getText().toString(),
                binding.spCiclosAddAlumno.getSelectedItem().toString(), letra
                );

        return alumno;
    }
}