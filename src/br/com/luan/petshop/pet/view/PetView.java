package br.com.luan.petshop.pet.view;

import br.com.luan.petshop.owner.controller.OwnerController;
import br.com.luan.petshop.owner.model.Owner;
import br.com.luan.petshop.pet.controller.PetController;
import br.com.luan.petshop.pet.model.Pet;
import br.com.luan.petshop.pet.model.Size;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class PetView {

    private Scanner scanner = new Scanner(System.in);
    private PetController petController = new PetController();
    private OwnerController ownerController = new OwnerController();


    public void App(){
        System.out.println("Bem Vindo ao PetShop");
        System.out.println("O que deseja?");
        System.out.print("Digite [1]Para Cadastrar; [2]Para Atualizar; [3] Para ver registros; [4]Para deletar: ");
        long option = scanner.nextLong();
        scanner.nextLine();

        if(option == 1){
            drawCreateView();
        }else if(option == 2){
            drawUpdate();
        }else if(option == 3){
            drawSearchView("");
        }else if(option == 4){
            drawDelete();
        }else{
            System.out.print("Opção inválida");
        }
        scanner.close();
    }



    public void drawCreateView() {
        System.out.println("************* CADASTRO DE PET *****************");

        System.out.print("Nome do dono: ");
        String ownerName = scanner.nextLine();

        System.out.print("Telefone do dono: ");
        String ownerPhone = scanner.nextLine();

        System.out.println("-----------------------------------------------");

        System.out.print("Nome do pet: ");
        String petName = scanner.nextLine();

        System.out.print("Raça do pet: ");
        String petBreed = scanner.nextLine();

        System.out.print("Peso do pet: ");
        Float petWeight = scanner.nextFloat();

        System.out.print("Porte do pet [1] PEQUENO, [2] MÉDIO, [3] GRANDE: ");
        Integer petSize = scanner.nextInt();

        scanner.close();

        System.out.println("\nCadastrando...");

        Owner owner = new Owner();
        owner.setName(ownerName);
        owner.setPhone(ownerPhone);

        Pet pet = new Pet();
        pet.setWeight(petWeight);
        pet.setSize(Size.getSizeByIndex(petSize));
        pet.setBreed(petBreed);
        pet.setName(petName);

        this.create(pet, owner);
    }

    private void create(Pet pet, Owner owner) {
        owner = ownerController.create(owner);

        pet.setOwnerId(owner.getId());

        petController.create(pet);
    }

    public void drawSearchView(){
        drawSearchView("");
    }

    private void drawSearchView(String name){

        List<Pet> petList = petController.search(name);
        for(int i= 0; i < petList.size(); i++) {
            System.out.println(petList.get(i));
        }
        System.out.println("");
        System.out.println("(Digite[-1] Para cancelar a busca)");
        System.out.print("Digite o nome do pet para a busca ou cancele: ");
        name = scanner.nextLine();
        if("-1".equals(name)){
           return;
        }
        drawSearchView(name);
        scanner.close();
    }

    public void drawDelete(){
        System.out.println("**************Deletar**************");

        System.out.print("Deseja deletar algum registro?(s/n): ");
        String result = scanner.nextLine();
        if("s".equals(result)){
            System.out.print("Digite o id do pet que deseja deletar: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            petController.delete(id);
        }
        drawSearchView();
        scanner.close();
    }


    public void drawUpdate(){
        System.out.println("**************Atualizar**************");

        System.out.print("Digite o id do registro que deseja atualizar? ");
        Long id = scanner.nextLong();

        Pet pet = petController.findById(id);
        System.out.println(pet);

        pet.setId(pet.getId());

        scanner.nextLine();
        System.out.println("");
        System.out.println("Para pergunta responda com (s/n)");
        System.out.print("Deseja mudar o nome?: ");
        String response = scanner.nextLine();
        if("s".equals(response)){
            System.out.print("Digite o novo nome: ");
            String name = scanner.nextLine();
            pet.setName(name);
        }

        System.out.println("");
        System.out.print("Deseja mudar a raça?: ");
        response = scanner.nextLine();
        if("s".equals(response)){
            System.out.print("Raça do pet: ");
            String breed = scanner.nextLine();
            pet.setBreed(breed);
        }

        System.out.println("");
        System.out.print("Deseja mudar o peso?: ");
        response = scanner.nextLine();
        if("s".equals(response)){
            System.out.print("Peso do pet: ");
            float weight = scanner.nextFloat();
            pet.setWeight(weight);
            scanner.nextLine();
        }

        System.out.println("");
        System.out.print("Deseja mudar o porte?: ");
        response = scanner.nextLine();
        if("s".equals(response)){
            System.out.print("Porte do pet [1] PEQUENO, [2] MÉDIO, [3] GRANDE: ");
            int petSize = scanner.nextInt();
            if(0 != petSize && petSize < 4){
                pet.setSize(Size.getSizeByIndex(petSize));
            }else{
                System.out.println("Valor inválido.");
            }
        }

        petController.update(pet);
        scanner.close();
    }

}
