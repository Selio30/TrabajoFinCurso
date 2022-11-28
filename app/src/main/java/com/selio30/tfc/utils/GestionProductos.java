package com.selio30.tfc.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.selio30.tfc.db.Database;
import com.selio30.tfc.entity.Almacen;
import com.selio30.tfc.entity.Formato;
import com.selio30.tfc.entity.Inventario;
import com.selio30.tfc.entity.InventarioProducto;
import com.selio30.tfc.entity.Localizacion;
import com.selio30.tfc.entity.Producto;
import com.selio30.tfc.entity.ProductoAlmacen;
import com.selio30.tfc.entity.ProductoHabituales;
import com.selio30.tfc.entity.ProductoUbicacion;
import com.selio30.tfc.entity.Tipo;
import com.selio30.tfc.entity.Ubicacion;
import com.selio30.tfc.viewmodel.DatabaseViewModel;
import com.selio30.tfc.viewmodel.ProductoViewModel;
import com.selio30.tfc.viewmodel.VolleyViewModel;
import com.selio30.tfc.webservice.AlmacenService;
import com.selio30.tfc.webservice.FormatoService;
import com.selio30.tfc.webservice.InventarioProductoService;
import com.selio30.tfc.webservice.InventarioService;
import com.selio30.tfc.webservice.LocalizacionService;
import com.selio30.tfc.webservice.ProductoAlmacenService;
import com.selio30.tfc.webservice.ProductoHabitualService;
import com.selio30.tfc.webservice.ProductoService;
import com.selio30.tfc.webservice.ProductoUbicacionService;
import com.selio30.tfc.webservice.TipoService;
import com.selio30.tfc.webservice.UbicacionService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class GestionProductos {
    /**
     * Atributos de la calse
     **/
    private MutableLiveData<List<Almacen>> almacenMutableListData;
    private MutableLiveData<List<Formato>> formatoMutableListData;
    private MutableLiveData<List<Inventario>> inventarioMutableListData;
    private MutableLiveData<List<InventarioProducto>> inventarioProductoMutableListData;
    private MutableLiveData<List<Localizacion>> localizacionMutableListData;
    private MutableLiveData<List<Producto>> productoMutableListData;
    private MutableLiveData<List<ProductoAlmacen>> productoAlmacenMutalbeListData;
    private MutableLiveData<List<ProductoHabituales>> productoHabitualMutableListData;
    private MutableLiveData<List<ProductoUbicacion>> productoUbicacionMutableListData;
    private MutableLiveData<List<Tipo>> tipoMutableListData;
    private MutableLiveData<List<Ubicacion>> ubicacionMutableListData;
    private List<Almacen> almacenList;
    private List<Formato> formatoList;
    private List<Inventario> inventarioList;
    private List<InventarioProducto> inventarioProductoList;
    private List<Localizacion> localizacionList;
    private List<Producto> productoList;
    private List<ProductoAlmacen> productoAlmacenList;
    private List<ProductoHabituales> productoHabitualesList;
    private List<ProductoUbicacion> productoUbicacionList;
    private List<Tipo> tipoList;
    private List<Ubicacion> ubicacionList;

    private Almacen almacen;
    private Formato formato;
    private Inventario inventario;
    private InventarioProducto inventarioProducto;
    private Localizacion localizacion;
    private Producto producto;
    private ProductoAlmacen productoAlmacen;
    private ProductoHabituales productoHabituales;
    private ProductoUbicacion productoUbicacion;
    private Tipo tipo;
    private Ubicacion ubicacion;
    private Database database;

    private ProductoViewModel productoViewModel;
    private DatabaseViewModel databaseViewModel;

    private LifecycleOwner lifecycleOwner;
    private Context context;
    private Activity activity;
    private View view;

    private AlmacenService almacenService;
    private FormatoService formatoService;
    private InventarioService inventarioService;
    private InventarioProductoService inventarioProductoService;
    private LocalizacionService localizacionService;
    private ProductoService productoService;
    private ProductoAlmacenService productoAlmacenService;
    private ProductoHabitualService productoHabitualService;
    private ProductoUbicacionService productoUbicacionService;
    private TipoService tipoService;
    private UbicacionService ubicacionService;
    private VolleyViewModel volleyViewModel;

    private Date date;

    public GestionProductos() {
    }

    public GestionProductos(LifecycleOwner lifecycleOwner, Context context, Activity activity, View view) {
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.activity = activity;
        this.view = view;

        this.productoViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ProductoViewModel.class);
        this.databaseViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(DatabaseViewModel.class);
        this.database = databaseViewModel.getDatabase(context);

        //VOLLEY
        this.volleyViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(VolleyViewModel.class);
        this.almacenService = new AlmacenService(context, view);
        this.formatoService = new FormatoService(context, view);
        this.inventarioService = new InventarioService(context, view);
        this.inventarioProductoService = new InventarioProductoService(context, view);
        this.localizacionService = new LocalizacionService(context, view);
        this.productoService = new ProductoService(context, view);
        this.productoAlmacenService = new ProductoAlmacenService(context, view);
        this.productoHabitualService = new ProductoHabitualService(context, view);
        this.productoUbicacionService = new ProductoUbicacionService(context, view);
        this.tipoService = new TipoService(context, view);
        this.ubicacionService = new UbicacionService(context, view);
    }

    /**
     * Inserción del inventario en la BBDD.
     */
    public void addInventarioProducto() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                date = new Date();

                String strDateFormat = "yyyy-MM-dd  HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
                Log.d("FECHA", dateFormat.format(date));

                inventario = new Inventario();
                inventario.setId(database.inventarioDAO().getLastId() + 1);
                Log.d("ID", String.valueOf(database.inventarioDAO().getLastId() + 1));
                inventario.setFecha(dateFormat.format(date));
                inventario.setId_localizacion("0");
                Log.d("INV", String.valueOf(inventario));

                try {
                    database.inventarioDAO().insert(inventario);
                    //inventarioService.create(inventario);

                    List<Producto> productos = database.productoDAO().getAll();
                    Log.d("P", String.valueOf(productos));
                    for (Producto p : productos) {
                        if (!Objects.equals(p.getStock(), "0") && !Objects.equals(p.getStock(), "")) {
                            inventarioProducto = new InventarioProducto(p.getId(), database.inventarioDAO().getLastId(), p.getStock(), "0");
                            database.inventarioDAO().insertInventarioProducto(inventarioProducto);
                        }
                    }
                    Log.d("PROD", String.valueOf(inventarioProducto));
                } catch (Exception e) {
                    Log.d("ERROR", e.getMessage());
                }
            }
        };
        thread.start();
    }


    /**
     * Hacemos la busqueda de todas las peliculas de la tabla productos (Room).
     **/
    public void getProductos() {
        MutableLiveData<List<Ubicacion>> listMutableLiveData_DB = productoViewModel.getUbicacion_DB();

        listMutableLiveData_DB.getValue().clear();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                List<Ubicacion> ubi = database.ubicacionDAO().getAll();
                for (Ubicacion u : ubi) {

                    List<Producto> productos = database.ubicacionDAO().getProductosUbicacionSelected(u.getId());
                    if (productos.size() != 0) {
                        u.setProductos(productos);
                        listMutableLiveData_DB.getValue().add(u);
                    }
                }
                listMutableLiveData_DB.postValue(listMutableLiveData_DB.getValue());
                productoViewModel.setUbicacion_DB(listMutableLiveData_DB);
            }
        };
        thread.start();
    }

    /**
     * Hacemos la busqueda de todas las peliculas de la tabla productos (Room).
     **/
    public void getProductosHabitual() {
        MutableLiveData<List<Ubicacion>> listMutableLiveData_DB = productoViewModel.getUbicacion_DB();

        listMutableLiveData_DB.getValue().clear();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                List<Ubicacion> ubi = database.ubicacionDAO().getAll();
                for (Ubicacion u : ubi) {

                    List<Producto> productos = database.ubicacionDAO().getProductosUbicacionSelected(u.getId());
                    if (productos.size() != 0 && Objects.equals(producto.getId(), productoHabituales.getId_producto())) {
                        u.setProductos(productos);
                        listMutableLiveData_DB.getValue().add(u);
                    }
                }
                listMutableLiveData_DB.postValue(listMutableLiveData_DB.getValue());
                productoViewModel.setUbicacion_DB(listMutableLiveData_DB);
            }
        };
        thread.start();
    }


    /** PARTE READ DE LA BASE DE DATOS REMOTA **/

    /**
     * Metodo que llama al metodo de la clase Almacen que trae los registros
     **/
    public void getRemoteAlmacen() {
        almacenService.read();

        volleyViewModel.getAlmacens().observe((LifecycleOwner) activity, new Observer<List<Almacen>>() {
            @Override
            public void onChanged(List<Almacen> almacens) {
                insertAlmacenRoom();
            }
        });
    }


    /**
     * Metodo que llama al metodo de la clase Formato que trae los registros
     */
    public void getRemoteFormato() {
        formatoService.read();

        volleyViewModel.getFormatos().observe((LifecycleOwner) activity, new Observer<List<Formato>>() {
            @Override
            public void onChanged(List<Formato> formatoes) {
                insertFormatoRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase Inventario que trae los registros
     */
    public void getRemoteInventario() {
        inventarioService.read();

        volleyViewModel.getInventarios().observe((LifecycleOwner) activity, new Observer<List<Inventario>>() {
            @Override
            public void onChanged(List<Inventario> inventarios) {
                insertInventarioRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase InventarioProductos que trae los registros
     */
    public void getRemoteInventarioProducto() {
        inventarioProductoService.read();

        volleyViewModel.getInventarioproductos().observe((LifecycleOwner) activity, new Observer<List<InventarioProducto>>() {
            @Override
            public void onChanged(List<InventarioProducto> inventarioProductos) {
                insertInventarioProductoRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase Localizacion que trae los registros
     */
    public void getRemoteLocalizacion() {
        localizacionService.read();

        volleyViewModel.getLocalizacions().observe((LifecycleOwner) activity, new Observer<List<Localizacion>>() {
            @Override
            public void onChanged(List<Localizacion> localizacions) {
                insertLocalizacionRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase Producto que trae los registros
     **/
    public void getRemoteProducto() {
        productoService.read();

        volleyViewModel.getProductos().observe((LifecycleOwner) activity, new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                insertProdRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase ProductoAlmacen que trae los registros
     **/
    public void getRemoteProductoAlmacen() {
        productoAlmacenService.read();

        volleyViewModel.getProductoalmacens().observe((LifecycleOwner) activity, new Observer<List<ProductoAlmacen>>() {
            @Override
            public void onChanged(List<ProductoAlmacen> productoAlmacens) {
                insertProdAlmRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase ProductoHabitual que trae los registros
     **/
    public void getRemoteProductoHabitual() {
        productoHabitualService.read();

        volleyViewModel.getProductohabituales().observe((LifecycleOwner) activity, new Observer<List<ProductoHabituales>>() {
            @Override
            public void onChanged(List<ProductoHabituales> productoHabituales) {
                insertProdHabiRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase ProductoUbicacion que trae los registros
     **/
    public void getRemoteProductoUbicacion() {
        productoUbicacionService.read();

        volleyViewModel.getProductoubicacions().observe((LifecycleOwner) activity, new Observer<List<ProductoUbicacion>>() {
            @Override
            public void onChanged(List<ProductoUbicacion> productoUbicacions) {
                insertProdUbiRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase Tipo que trae los registros
     */
    public void getRemoteTipo() {
        tipoService.read();

        volleyViewModel.getTipos().observe((LifecycleOwner) activity, new Observer<List<Tipo>>() {
            @Override
            public void onChanged(List<Tipo> tipos) {
                insertTipoRoom();
            }
        });
    }

    /**
     * Metodo que llama al metodo de la clase Ubicacion que trae los registros
     **/
    public void getRemoteUbicacion() {
        ubicacionService.read();

        volleyViewModel.getUbicacions().observe((LifecycleOwner) activity, new Observer<List<Ubicacion>>() {
            @Override
            public void onChanged(List<Ubicacion> ubicacions) {
                insertUbiRoom();
            }
        });
    }

    /** Inserción de registros **/

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla almacen
     */
    private void insertAlmacenRoom() {
        almacenMutableListData = volleyViewModel.getAlmacens();
        almacenList = almacenMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (Almacen a : almacenList) {
                    Almacen alm = database.almacenDAO().getById(a.getId());
                    if (alm == null) {
                        database.almacenDAO().insert(a);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla formato
     */
    private void insertFormatoRoom() {
        formatoMutableListData = volleyViewModel.getFormatos();
        formatoList = formatoMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (Formato f : formatoList) {
                    Formato form = database.formatoDAO().getById(f.getId());
                    if (form == null) {
                        database.formatoDAO().insert(f);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla Inventario
     */
    private void insertInventarioRoom() {
        inventarioMutableListData = volleyViewModel.getInventarios();
        inventarioList = inventarioMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (Inventario i : inventarioList) {
                    Inventario inv = database.inventarioDAO().getById(String.valueOf(i.getId()));
                    if (inv == null) {
                        database.inventarioDAO().insert(i);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla inventarioProducto
     */
    private void insertInventarioProductoRoom() {
        inventarioProductoMutableListData = volleyViewModel.getInventarioproductos();
        inventarioProductoList = inventarioProductoMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (InventarioProducto i : inventarioProductoList) {
                    InventarioProducto inpr = database.inventarioDAO().getOneInventarioProducto(String.valueOf(i.getId_inventario()), i.getId_producto());
                    if (inpr == null) {
                        database.inventarioDAO().insertInventarioProducto(i);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla localizacion
     */
    private void insertLocalizacionRoom() {
        localizacionMutableListData = volleyViewModel.getLocalizacions();
        localizacionList = localizacionMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (Localizacion l : localizacionList) {
                    Localizacion loc = database.localizacionDAO().getById(l.getId());
                    if (loc == null) {
                        database.localizacionDAO().insert(l);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla producto
     **/
    private void insertProdRoom() {
        productoMutableListData = volleyViewModel.getProductos();
        productoList = productoMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (Producto p : productoList) {
                    Producto prod = database.productoDAO().getById(p.getId());
                    if (prod == null) {
                        database.productoDAO().insert(p);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla producto
     **/
    private void insertProdAlmRoom() {
        productoAlmacenMutalbeListData = volleyViewModel.getProductoalmacens();
        productoAlmacenList = productoAlmacenMutalbeListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (ProductoAlmacen p : productoAlmacenList) {
                    ProductoAlmacen pral = database.almacenDAO().getOneProductoAlmacen(p.getId_almacen(), p.getId_producto());
                    if (pral == null) {
                        database.almacenDAO().insertProductoAlmacen(p);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla producto
     **/
    private void insertProdHabiRoom() {
        productoHabitualMutableListData = volleyViewModel.getProductohabituales();
        productoHabitualesList = productoHabitualMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (ProductoHabituales p : productoHabitualesList) {
                    ProductoHabituales prhb = database.localizacionDAO().getOneProductoHabitual(p.getId_localizacion(), p.getId_producto());
                    if (prhb == null) {
                        database.localizacionDAO().insertProductoHabitual(p);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla producto
     **/
    private void insertProdUbiRoom() {
        productoUbicacionMutableListData = volleyViewModel.getProductoubicacions();
        productoUbicacionList = productoUbicacionMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (ProductoUbicacion p : productoUbicacionList) {
                    ProductoUbicacion prub = database.ubicacionDAO().getOneProductoUbicacion(p.getId_ubicacion(), p.getId_producto());
                    if (prub == null) {
                        database.ubicacionDAO().insertProductoUbicacion(p);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla tipo
     */
    private void insertTipoRoom() {
        tipoMutableListData = volleyViewModel.getTipos();
        tipoList = tipoMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (Tipo t : tipoList) {
                    Tipo tip = database.tipoDAO().getById(t.getId());
                    if (tip == null) {
                        database.tipoDAO().insert(t);
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Insertamos los registros de la base de datos remota en el Room, en este caso a la tabla ubicacion
     **/
    private void insertUbiRoom() {
        ubicacionMutableListData = volleyViewModel.getUbicacions();
        ubicacionList = ubicacionMutableListData.getValue();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (Ubicacion u : ubicacionList) {
                    Ubicacion ubi = database.ubicacionDAO().getById(u.getId());
                    if (ubi == null) {
                        database.ubicacionDAO().insert(u);
                    }
                }
            }
        };
        thread.start();
    }
}