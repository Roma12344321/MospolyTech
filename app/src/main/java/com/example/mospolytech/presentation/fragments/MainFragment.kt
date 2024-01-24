package com.example.mospolytech.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mospolytech.R
import com.example.mospolytech.databinding.FragmentMainBinding
import com.example.mospolytech.domain.Direction
import com.example.mospolytech.presentation.viewmodel.DirectionApp
import com.example.mospolytech.presentation.viewmodel.MainViewModel
import com.example.mospolytech.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("binding = null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private val component by lazy {
        (requireActivity().application as DirectionApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewFavourite.setOnClickListener {
            launchFavouriteFragment()
        }
        addDirections()
        binding.buttonFind.setOnClickListener {
            val first = binding.spinnerdir.selectedItem.toString()
            val second = binding.spinnerdir2.selectedItem.toString()
            val third = binding.spinnerdir3.selectedItem.toString()
            val level = binding.spinnerEdLevel.selectedItem.toString()
            val list = mutableListOf<String>().apply {
                add(first)
                add(second)
                add(third)
                add(level)
            }
            if (list.contains("Русский") && list.contains("Математика(Профиль)") && (list.contains("Информатика") || list.contains(
                    "Физика"
                )) && list.contains("Бакалавриат")
            ) {
                launchDirectionListFragment(DirectionListFragment.RUS_MATH_INF)
            } else {
                launchDirectionListFragment(DirectionListFragment.EMPTY)
            }
        }
    }


    private fun launchFavouriteFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FavouriteFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchDirectionListFragment(obj: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DirectionListFragment.newInstance(obj))
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addDirections() {
        viewModel.allFavouriteDirection.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                viewModel.addFavouriteDirection(
                    Direction(
                        1,
                        requireContext().getString(R.string._01_03_02),
                        "224",
                        "Прикладная математика и информатика – это направление, которое объединяет в себе 2 популярные и важные области знаний – математика и информатика. Данное направление предназначено для подготовки специалистов, которые после обучения смогут объединить методы математики и компьютерные технологии для дальнейшей работы в автомобильной отрасли, а также в области проектирования и расчета деталей и узлов беспилотных транспортных средств \n" +
                                "В связи с тем, что в настоящее время повышается уровень развития автомобилей (появляются новые виды или улучшаются старые), каждый разработанный с нуля автомобиль содержит в себе все новые и новые технические начинки – данное направление является очень актуальным, любое предприятие будет радо взять к себе такого специалиста  \n",
                        "Программист \n" +
                                "Инженер – расчетчик \n" +
                                "Инженер – испытатель\n",
                        "Данное направление расскажет Вам про всевозможные знания, которые потребуются для разработки прикладного программного обеспечения, а затем все закрепить на практике \n" +
                                "Данное направление поможет Вам применить программные комплексы математического и компьютерного моделирования \n" +
                                "Данное направление научит расскажет Вам про методы прикладного программирования, поможет научить пользоваться языками прикладного программирования, которые в настоящий момент являются одними из самых популярных и востребованных языков программирования (C, C++, Java) \n" +
                                "После окончания обучения на данном направлении, у Вас не возникнет трудностей с поиском работы, так как существуют партнеры-работодатели (НАМИ, РКС, ИМАШ РАН, НПО НАУКА) \n",
                        "Русский Математика Информатика"
                    )
                )
                viewModel.addFavouriteDirection(
                    Direction(
                        2,
                        "23.05.01 «Спортивные транспортные средства»",
                        "233",
                        "Спортивные транспортные средства – это направление, которое ориентированно на формирование креативности, инновационного, а также предпринимательского мышления и подготавливает специалистов в области разработки транспортных средств, умеющего ставить проблему и сразу находить для нее решения в автомобильной отрасли при условиях неопределенности. Данное направление также развивает системное мышление, лидерские качества.",
                        "Инженер – конструктор гоночной команды \n" +
                                "Ведущий инженер – конструктор \n" +
                                "Главный специалист гоночной команды \n",
                        "Данные профессии имеют общественную значимость, так как инженеры причастны ко всем устройствам, которые окружают человека в настоящее время \n" +
                                "Данные профессии очень востребованы в настоящее время и будет востребованы еще не один десяток лет, так как ни одно производство не обходится без специалистов в данной области, такие специалисты востребованы от начала проектирования до его постройки и реализации \n" +
                                "После окончания обучения на данном направлении, у Вас не возникнет трудностей с поиском работы, так как существуют партнеры-работодатели (НАМИ, RENAULT, KAMAZ, АВТОВАЗ) \n",
                        "Русский Математика Информатика"
                    )
                )
                viewModel.addFavouriteDirection(
                    Direction(
                        3,
                        "08.03.01 Строительство (Промышленное и гражданское строительство, Теплогазоснабжение, вентиляция, водоснабжение и водоотведение)",
                        "213",
                        "Программа обучения \"Промышленное и гражданское строительство\"\n" +
                                "предоставляет студентам глубокие знания и навыки, необходимые для работы\n" +
                                "в сфере строительства. Она охватывает различные аспекты проектирования и\n" +
                                "конструирования зданий и сооружений.\n" +
                                "Программа включает изучение основных принципов и методов расчета\n" +
                                "конструкций, включая использование математических моделей и инженерных\n" +
                                "программ. Также уделяется внимание изучению технологий, применяемых в\n" +
                                "строительстве, и организации строительного процесса.\n" +
                                "Знания и навыки, полученные в рамках программы, делают выпускников\n" +
                                "\"Промышленного и гражданского строительства\" востребованными\n" +
                                "специалистами как на российском рынке труда, так и за его пределами",
                        "Бакалавр по специальности «Промышленное и гражданское\n" +
                                "строительство» может работать в качестве:\n" +
                                "-инженера по обследованию зданий и сооружений;\n" +
                                "-инженера-проектировщика;\n" +
                                "-инженера-конструктора;\n" +
                                "-прораба;\n" +
                                "-сотрудника научно-исследовательских организаций;",
                        "Данная профессия имеет много плюсов:\n" +
                                "-актуальность профессии;\n" +
                                "2\n" +
                                "-доступное образование — получить одну из строительных\n" +
                                "специальностей можно после 9 класса;\n" +
                                "-свободный график – специалист, обладающий\n" +
                                "профессиональными навыками в сфере строительства, может самостоятельно\n" +
                                "предлагать свои услуги, указывать на них цену по своему усмотрению и\n" +
                                "работать по свободному графику;\n" +
                                "-высокая оплата труда;\n" +
                                "-обретение профессиональных навыков, которые можно применять\n" +
                                "в обычной жизни;\n" +
                                "-возможность переквалификации;\n" +
                                "-технологичность",
                        "Русский Математика Информатика"

                    )
                )
                viewModel.addFavouriteDirection(
                    Direction(
                        4,
                        "08.05.01 Строительство уникальных зданий и сооружений",
                        "213",
                        "",
                        "",
                        "",
                        "Русский Математика Информатика"
                    )
                )
                viewModel.addFavouriteDirection(
                    Direction(
                        5,
                        "09.03.01.01 Веб-технологии",
                        "244",
                        "Информатика и вычислительная техника - это обширное направление,\n" +
                                "связанное с изучением компьютерных систем, программного обеспечения,\n" +
                                "аппаратных средств, алгоритмов, их разработкой, управлением и\n" +
                                "применением в различных областях. Оно включает в себя множество\n" +
                                "подразделений и специализаций, таких как программирование, базы данных,\n" +
                                "сетевые технологии, искусственный интеллект, робототехника,\n" +
                                "кибербезопасность и многое другое.",
                        "1. Программист: Создание и разработка программного обеспечения, вебсайтов, мобильных приложений и других компьютерных приложений.\n" +
                                "2. Системный администратор: Управление компьютерными сетями и\n" +
                                "серверами, обеспечение их безопасности и стабильной работы.\n" +
                                "3. Инженер по аппаратному обеспечению: Разработка, сборка и\n" +
                                "обслуживание компьютерных и серверных систем.\n" +
                                "4. Аналитик данных: Анализ и интерпретация данных с целью получения\n" +
                                "ценной информации для бизнеса.\n" +
                                "5. Специалист по кибербезопасности: Защита компьютерных систем от\n" +
                                "киберугроз и вирусов.\n" +
                                "6. Специалист по искусственному интеллекту: Разработка и обучение\n" +
                                "алгоритмов машинного обучения и нейронных сетей.",
                        "-Высокий спрос на специалистов: С ростом компьютеризации и\n" +
                                "цифровой трансформации во всех отраслях спрос на специалистов по\n" +
                                "информатике и вычислительной технике растет постоянно.\n" +
                                "-Возможности для карьерного роста: В этой области есть широкие\n" +
                                "возможности для развития и 4. Заработная плата: Профессионалы в\n" +
                                "этой области обычно получают высокую заработную плату.\n" +
                                "-Гибкость и удаленная работа: Многие задачи можно выполнять\n" +
                                "удаленно, что обеспечивает гибкий график работы.\n",
                        "Русский Математика Информатика"
                    )
                )
                viewModel.addFavouriteDirection(
                    Direction(
                        6,
                        "09.03.01.02 Интеграция и программирование в САПР",
                        "244",
                        "Информатика и вычислительная техника - это обширное направление,\n" +
                                "связанное с изучением компьютерных систем, программного обеспечения,\n" +
                                "аппаратных средств, алгоритмов, их разработкой, управлением и\n" +
                                "применением в различных областях. Оно включает в себя множество\n" +
                                "подразделений и специализаций, таких как программирование, базы данных,\n" +
                                "сетевые технологии, искусственный интеллект, робототехника,\n" +
                                "кибербезопасность и многое другое.",
                        "1. Программист: Создание и разработка программного обеспечения, вебсайтов, мобильных приложений и других компьютерных приложений.\n" +
                                "2. Системный администратор: Управление компьютерными сетями и\n" +
                                "серверами, обеспечение их безопасности и стабильной работы.\n" +
                                "3. Инженер по аппаратному обеспечению: Разработка, сборка и\n" +
                                "обслуживание компьютерных и серверных систем.\n" +
                                "4. Аналитик данных: Анализ и интерпретация данных с целью получения\n" +
                                "ценной информации для бизнеса.\n" +
                                "5. Специалист по кибербезопасности: Защита компьютерных систем от\n" +
                                "киберугроз и вирусов.\n" +
                                "6. Специалист по искусственному интеллекту: Разработка и обучение\n" +
                                "алгоритмов машинного обучения и нейронных сетей.",
                        "-Высокий спрос на специалистов: С ростом компьютеризации и\n" +
                                "цифровой трансформации во всех отраслях спрос на специалистов по\n" +
                                "информатике и вычислительной технике растет постоянно.\n" +
                                "-Возможности для карьерного роста: В этой области есть широкие\n" +
                                "возможности для развития и 4. Заработная плата: Профессионалы в\n" +
                                "этой области обычно получают высокую заработную плату.\n" +
                                "-Гибкость и удаленная работа: Многие задачи можно выполнять\n" +
                                "удаленно, что обеспечивает гибкий график работы.\n",
                        "Русский Математика Информатика"
                    )
                )
            }
        }
    }
}