import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {FuncCompany} from './funccompany'

@Entity("t_func_type",'tunnel')
export class FuncType extends BaseEntity{
	@Id()
	@Column({
		name:'func_type_id',
		type:'int',
		nullable:false
	})
	private funcTypeId:number;

	@Column({
		name:'func_type_name',
		type:'string',
		nullable:true
	})
	private funcTypeName:string;

	@OneToMany({entity:'FuncCompany',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'funcType',eager:false})
	private funcCompanys:Array<FuncCompany>;

	public getFuncTypeId():number{
		return this.funcTypeId;
	}
	public setFuncTypeId(value:number){
		this.funcTypeId = value;
	}

	public getFuncTypeName():string{
		return this.funcTypeName;
	}
	public setFuncTypeName(value:string){
		this.funcTypeName = value;
	}

	public getFuncCompanys():Array<FuncCompany>{
		return this.funcCompanys;
	}
	public setFuncCompanys(value:Array<FuncCompany>){
		this.funcCompanys = value;
	}

}