namespace Data
{
    using Domain.Entity;
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    

    [Table("dbirmc.event")]
    public  class _event
    {
   
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public _event()
        {
            ticketevent = new HashSet<ticketevent>();
        }

        [Key]
        public int id_Ev { get; set; }

        [Column(TypeName = "bit")]
        public bool? canceled { get; set; }

        public int? capacity { get; set; }

       
        public category cat { get; set; }

        [Column(TypeName = "date")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime creationDate { get; set; }

        [StringLength(255)]
        public string description { get; set; }
     
        [Column(TypeName = "date")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime? endDate { get; set; }

        [Column(TypeName = "bit")]
        public bool? etat { get; set; }

        
        [DataType(DataType.ImageUrl), Display(Name = "Image")]
        public string image { get; set; }

        public int? nbPart { get; set; }

        [Column(TypeName = "date")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime? startDate { get; set; }

        [StringLength(255)]
        public string title { get; set; }

        [Column(TypeName = "bit")]
        public bool valide { get; set; }

        public int? user_id { get; set; }

        public virtual user user { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ticketevent> ticketevent { get; set; }
    }
}
