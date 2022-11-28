package com.example.taskmanager.ui.onBoarding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.data.model.OnBoard

class OnBoardingAdapter(val onClick:()->Unit) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val arrayList = arrayListOf <OnBoard> (
        OnBoard(
            R.raw.manager,
            " Отечественный продукт ",
            "   Каждый этап создания проходил в Кыргызстане, от разработки и до запуска и поддержки "
        ),
        OnBoard(
            R.raw.laptop,
            " Самый удобный сервис ",
            "Доступная цена, круглосуточная поддержка,наличный и безналичный расчет"
        ),
        OnBoard(
            R.raw.taxi,
            " У нас заботятся об экологии",
            "  В автопарке абсолюнтно новые машины на газе и электричестве,работают без вреда для окружающей среды "
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(

            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(arrayList[position])

    }

    override fun getItemCount(): Int {
        return arrayList.size


    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoard: OnBoard) {
            binding.btnStart.isVisible = adapterPosition == arrayList.lastIndex
            binding.skip.isVisible = adapterPosition != arrayList.lastIndex
            binding.tvTITLE.text = onBoard.title
            binding.tvDESC.text = onBoard.desc

            binding.skip.setOnClickListener {
                onClick()
            }
            binding.btnStart.setOnClickListener {
                onClick()
            }

            if (adapterPosition == 0) {
               binding.image.setAnimation(R.raw.taxi)
                binding.ind1.setImageResource(R.drawable.selected)
                binding.ind2.setImageResource(R.drawable.unselected)
                binding.ind3.setImageResource(R.drawable.unselected)
                binding.back.isVisible = false
                binding.btnBack.isVisible = false
            }
            if (adapterPosition == 1) {
                binding.image.setAnimation(R.raw.manager)
                binding.ind1.setImageResource(R.drawable.unselected)
                binding.ind2.setImageResource(R.drawable.selected)
                binding.ind3.setImageResource(R.drawable.unselected)
                binding.back.isVisible = true
                binding.btnBack.isVisible = true
            }
            if (adapterPosition == 2) {
                binding.image.setAnimation(R.raw.laptop)
                binding.ind1.setImageResource(R.drawable.unselected)
                binding.ind2.setImageResource(R.drawable.unselected)
                binding.ind3.setImageResource(R.drawable.selected)
                binding.next.isVisible = false
                binding.btnNext.isVisible = false
                binding.imageSkip.isVisible = false
            }
        }


    }
}