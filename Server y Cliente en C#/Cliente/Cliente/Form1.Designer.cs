
namespace Cliente
{
    partial class Form1
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.mensajeTexto = new System.Windows.Forms.TextBox();
            this.boton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // mensajeTexto
            // 
            this.mensajeTexto.Location = new System.Drawing.Point(96, 198);
            this.mensajeTexto.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.mensajeTexto.Name = "mensajeTexto";
            this.mensajeTexto.Size = new System.Drawing.Size(612, 22);
            this.mensajeTexto.TabIndex = 0;
            // 
            // boton
            // 
            this.boton.Location = new System.Drawing.Point(353, 385);
            this.boton.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.boton.Name = "boton";
            this.boton.Size = new System.Drawing.Size(215, 73);
            this.boton.TabIndex = 1;
            this.boton.Text = "Enviar";
            this.boton.UseVisualStyleBackColor = true;
            this.boton.Click += new System.EventHandler(this.button1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1067, 554);
            this.Controls.Add(this.boton);
            this.Controls.Add(this.mensajeTexto);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox mensajeTexto;
        private System.Windows.Forms.Button boton;
    }
}

