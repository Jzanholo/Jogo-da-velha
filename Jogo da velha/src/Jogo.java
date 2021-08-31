import java.util.Scanner;
public class Jogo {
	//funcao de busca para ver se a posição ja foi ocupada
	public static int busca(char J[][], int M[][], int posicao)
	{
		int i,j;
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				if(M[i][j]==posicao) 
				{
					if(J[i][j]=='X'||J[i][j]=='O')
						return 1;
				}
			}
		}
		return 0;
	}
	
	//função para mostrar a matriz
	static void mostra_matriz(char J[][])
	{
		int i,j;
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				System.out.printf("%c ", J[i][j]);
				if(j!=2)
					System.out.print("| ");
			}
			System.out.print("\n");
		}
	}


	//mostrar posições da matriz
	static void matriz_posiçoes(int M [][] )
	{
		int i,j;
		System.out.print("\nPara marcar sua posição digite um numero de 0 a 8 para respectiva posição, por exemplo: \n");
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				System.out.printf("%s ", M[i][j]);
				if(j!=2) System.out.printf("| ");
			}
			System.out.print("\n");
		}
	}
	//verifica se ha vencedor
	public static int vencedor(char J[][])
	{
		int i, j=0;
		for(i=0;i<3;i++)
		{
				if(J[i][j]=='X'&&J[i][j+1]=='X'&&J[i][j+2]=='X')
					return 1;
				else if(J[i][j]=='O'&&J[i][j+1]=='O'&&J[i][j+2]=='O')
					return 1;
		}
		i=0;
		for(j=0;j<3;j++)
		{
				if(J[i][j]=='X'&&J[i+1][j]=='X'&&J[i+2][j]=='X')
					return 1;
				else if(J[i][j]=='O'&&J[i+1][j]=='O'&&J[i+2][j]=='O')
					return 1;
		}
		if(J[0][0]=='X'&&J[1][1]=='X'&&J[2][2]=='X') 
			return 1;
		else if(J[0][0]=='O'&&J[1][1]=='O'&&J[2][2]=='O') 
			return 1;
		else if(J[0][2]=='X'&&J[1][1]=='X'&&J[2][0]=='X') 
			return 1;
		else if(J[0][2]=='O'&&J[1][1]=='O'&&J[2][0]=='O') 
			return 1;
		return 0;
	}
	//função principal
	public static void main(String[] args) {
		int vencedor=0,posicao,flag,i,j, cont_empate=0,mostrar;
		Scanner ler = new Scanner(System.in);
		int [][] M = new int[3][3];
		char [][] J = new char[3][3];
		//inicializando matriz com suas devidas posições do jogo
		M[0][0]=0;  M[0][1]=1;  M[0][2]=2; 
		M[1][0]=3;  M[1][1]=4;  M[1][2]=5;
		M[2][0]=6;  M[2][1]=7;  M[2][2]=8;
		System.out.print("JOGO DA VELHA");
		System.out.print("\n--------------\n");
		System.out.print("\nDigite o nome do jogador 1(começa jogando): ");
		String jogador1=ler.nextLine();
		System.out.print("\nDigite o nome do jogador 2: ");
		String jogador2=ler.nextLine();
		matriz_posiçoes(M);
		while(vencedor!=1)
		{
			System.out.print("\n------------------------ ");
			System.out.printf("\n%s digite uma posição: ", jogador1);
		    posicao=ler.nextInt();
		    flag=busca(J,M,posicao);
		    while(posicao<0||posicao>8||flag!=0)
			{
				System.out.print("Posição incorreta ou ja ocupada");
				System.out.print("\nDigite novamente: ");
				posicao=ler.nextInt();
				flag=busca(J,M,posicao);
			}
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(M[i][j]==posicao) 
					{
						J[i][j]='X';
						cont_empate++;
						vencedor=vencedor(J);
						if(vencedor==1) {
							mostra_matriz(J);
							System.out.print("\n---------------------------------");
							System.out.printf("\nParabens %s você é o vencedor", jogador1);
							System.out.print("\n---------------------------------");
							return;
						}
						if(cont_empate==5) 
						{
							
							System.out.print("\n-----EMPATE-----");
							return;
						}
					}
				}
			}
			System.out.print("\n");
			mostra_matriz(J);
			System.out.print("\n------------------------ ");
			System.out.printf("\n%s digite uma posição: ", jogador2);
		    posicao=ler.nextInt();
		    flag=busca(J,M,posicao);
		    while(posicao<0||posicao>8||flag!=0)
			{
				System.out.print("Posição incorreta ou ja ocupada");
				System.out.print("\nDigite novamente: ");
				posicao=ler.nextInt();
				flag=busca(J,M,posicao);
			}
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(M[i][j]==posicao) 
					{
						J[i][j]='O';
						vencedor=vencedor(J);
						if(vencedor==1) {
							mostra_matriz(J);
							System.out.print("\n---------------------------------");
							System.out.printf("\nParabens %s você é o vencedor", jogador2);
							System.out.print("\n---------------------------------");
							return;
						}
					}
				}
			}
			System.out.print("\n");
			mostra_matriz(J);
		}
	}

}