using System.Data.SqlClient;

namespace WinFormsApp1
{
    
    public partial class Form1 : Form
    {
        string connectionString = @"Server=LAPTOP-RF4H25K3\MSSQLSERVER01;Database=Laborator1224-2SGBD2023;
        Integrated Security =true;TrustServerCertificate=true;";
        DataSet ds = new DataSet();
        SqlDataAdapter=new SqlDataAdapter();
        public Form1()
        {
            InitializeComponent();
        }
    }
}