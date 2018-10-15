using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Linq.Expressions;
using ServicePattern;


 
    namespace Service
    {
        /// <summary>
        ///     Add any custom business logic (methods) here
        /// </summary>
        public interface IEventService : IService<Data._event>
        {

        }

    }


