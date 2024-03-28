public class CPF {
    private CPFException validationError = new CPFException();
    private String numero;

    public CPF() {}

    public CPF(String numero) {
        this.numero = numero;

        if (!isValido()) {
            validationError.enviaExcessao(new CoreException.BusinessValidation(
                "CPF Inválido", CPFException.class.getSimpleName())
            );

            validationError.testeValidacao();
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroFormatado() {
        return Long.parseLong(numero).toString().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public boolean isValido() {
        int[] multiplicador1 = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] multiplicador2 = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

        String cpf = numero.trim().replace(".", "").replace("-", "");

        if (cpf.length() != 11) {
            return false;
        }

        String tempCpf = cpf.substring(0, 9);
        int soma = 0;

        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(tempCpf.charAt(i))) * multiplicador1[i];
        }

        int resto = soma % 11;
        int digito1;

        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        tempCpf = tempCpf + digito1;
        soma = 0;

        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(tempCpf.charAt(i))) * multiplicador2[i];
        }

        resto = soma % 11;
        int digito2;

        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        return cpf.endsWith(String.valueOf(digito1) + String.valueOf(digito2));
    }
}
