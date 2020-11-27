
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
            this.mensajeTexto.Location = new System.Drawing.Point(72, 161);
            this.mensajeTexto.Name = "mensajeTexto";
            this.mensajeTexto.Size = new System.Drawing.Size(460, 20);
            this.mensajeTexto.TabIndex = 0;
            // 
            // boton
            // 
            this.boton.Location = new System.Drawing.Point(265, 313);
            this.boton.Name = "boton";
            this.boton.Size = new System.Drawing.Size(161, 59);
            this.boton.TabIndex = 1;
            this.boton.Text = "Enviar";
            this.boton.UseVisualStyleBackColor = true;
            this.boton.Click += new System.EventHandler(this.button1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.boton);
            this.Controls.Add(this.mensajeTexto);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox mensajeTexto;
        private System.Windows.Forms.Button boton;
    }
}

