using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SistemaBecasWeb.Controllers
{
    public class AccesoController : Controller
    {
        // GET: Acceso
        public ActionResult Autenticar()
        {
            return View();
        }

        public ActionResult Registros()
        {
            return View();

        }
        public ActionResult datos_academicos()
        {
            return View();
        }

        public ActionResult postulacion_beca()
        {
            return View();
        }
    }
}